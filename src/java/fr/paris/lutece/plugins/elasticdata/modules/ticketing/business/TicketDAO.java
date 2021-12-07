/*
 * Copyright (c) 2002-2017, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.elasticdata.modules.ticketing.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.plugins.ticketing.business.category.TicketCategory;
import fr.paris.lutece.plugins.ticketing.business.category.TicketCategoryHome;
import fr.paris.lutece.plugins.ticketing.business.resourcehistory.DateActionWorkflow;
import fr.paris.lutece.plugins.ticketing.business.resourcehistory.ResourceWorkflowHistoryDAO;
import fr.paris.lutece.plugins.unittree.business.unit.Unit;
import fr.paris.lutece.plugins.unittree.business.unit.UnitHome;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * TicketDAO.
 */
public class TicketDAO
{

    /** The Constant SQL_QUERY_SELECTALL. */
    private static final String SQL_QUERY_SELECTALL_TO_INDEX_INCREMENTALLY = "SELECT id_ticket_category, date_create, date_close, id_unit, guid, id_ticket, channel.label label, s.name name, arrondissement FROM ticketing_ticket ticket join workflow_resource_workflow r on r.id_resource=ticket.id_ticket join workflow_state s on s.id_state = r.id_state join ticketing_channel channel on channel.id_channel=ticket.id_channel"
            + " WHERE ticket.date_update > ? OR ticket.date_create > ? OR ticket.date_close > ?";

    private static final String SQL_QUERY_SELECTALL_TO_INDEX               = "SELECT id_ticket_category, date_create, date_close, id_unit, guid, id_ticket, channel.label label, s.name name, arrondissement FROM ticketing_ticket ticket join workflow_resource_workflow r on r.id_resource=ticket.id_ticket join workflow_state s on s.id_state = r.id_state join ticketing_channel channel on channel.id_channel=ticket.id_channel WHERE date_update > DATE_SUB(NOW(), INTERVAL 1 DAY )";


    /**
     * Select all incrementally.
     *
     * @param plugin the plugin
     * @param lastIndexation the last indexation
     * @return the collection
     */
    public Collection<DataObject> selectAllIncrementally( Plugin plugin, Timestamp lastIndexation )
    {

        List<Unit> units = UnitHome.findAll( );

        Map<Integer, Unit> unitMap = new HashMap<>( );

        for ( Unit unit : units )
        {
            unitMap.put( unit.getIdUnit( ), unit );
        }

        List<TicketCategory> categories = TicketCategoryHome.getCategorysList( );

        Map<Integer, TicketCategory> catMap = new HashMap<>( );

        for ( TicketCategory cat : categories )
        {
            catMap.put( cat.getId( ), cat );
        }

        List<TicketDataObject> ticketDataObjectList = new ArrayList<>( );
        List<Integer> ids = new ArrayList<>( );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_TO_INDEX_INCREMENTALLY, plugin );

        daoUtil.setTimestamp( 1, lastIndexation );
        daoUtil.setTimestamp( 2, lastIndexation );
        daoUtil.setTimestamp( 3, lastIndexation );

        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            try
            {
                TicketDataObject ticket = dataToTicket( daoUtil, catMap, unitMap );
                ids.add( ticket.getIdTicket( ) );
                ticketDataObjectList.add( ticket );
            } catch ( Exception e )
            {
                AppLogService.error( e );
            }
        }

        daoUtil.free( );

        return fillTicketList( ids, ticketDataObjectList, plugin );
    }


    /**
     * Select all.
     *
     * @param plugin the plugin
     * @return the collection
     */
    public List<DataObject> selectAll( Plugin plugin )
    {
        List<Unit> units = UnitHome.findAll( );

        Map<Integer, Unit> unitMap = new HashMap<>( );

        for ( Unit unit : units )
        {
            unitMap.put( unit.getIdUnit( ), unit );
        }

        List<TicketCategory> categories = TicketCategoryHome.getCategorysList( );

        Map<Integer, TicketCategory> catMap = new HashMap<>( );

        for ( TicketCategory cat : categories )
        {
            catMap.put( cat.getId( ), cat );
        }

        List<TicketDataObject> ticketDataObjectList = new ArrayList<>( );
        List<Integer> ids = new ArrayList<>( );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL_TO_INDEX, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            try
            {
                TicketDataObject ticket = dataToTicket( daoUtil, catMap, unitMap );
                ids.add( ticket.getIdTicket( ) );
                ticketDataObjectList.add( ticket );
            } catch ( Exception e )
            {
                AppLogService.error( e );
            }
        }
        daoUtil.free( );

        return fillTicketList( ids, ticketDataObjectList, plugin );
    }

    /**
     * Data to ticket.
     *
     * @param daoUtil
     *            the dao util
     * @param catMap
     *            the cat map
     * @param unitMap
     *            the unit map
     * @return the ticket data object
     */
    private static TicketDataObject dataToTicket( DAOUtil daoUtil, Map<Integer, TicketCategory> catMap, Map<Integer, Unit> unitMap )
    {
        TicketDataObject ticket = new TicketDataObject( );
        TicketCategory category = catMap.get( daoUtil.getInt( "id_ticket_category" ) );
        if ( category != null )
        {
            ticket.setThematique( getParentCategory( category, catMap, 2 ) );
            ticket.setDomaine( getParentCategory( category, catMap, 1 ) );
            try
            {
                ticket.setSousThematique( getParentCategory( category, catMap, 3 ) );
            } catch ( NullPointerException e )
            {
                ticket.setSousThematique( "" );
            }

            try
            {
                ticket.setLocalisation( getParentCategory( category, catMap, 4 ) );
            } catch ( NullPointerException e )
            {
                ticket.setLocalisation( "" );
            }
        }
        ticket.setDateCreate( daoUtil.getDate( "date_create" ) );
        ticket.setDateClose( daoUtil.getDate( "date_close" ) );
        ticket.setGuid( daoUtil.getString( "guid" ) );
        ticket.setIdTicket( daoUtil.getInt( "id_ticket" ) );

        if ( ticket.getDateCreate( ) != null )
        {
            long diff = new Date( ).getTime( ) - ticket.getDateCreate( ).getTime( );
            long anciennete = TimeUnit.DAYS.convert( diff, TimeUnit.MILLISECONDS );
            ticket.setAnciennete( anciennete );
        }

        if ( ( ticket.getDateClose( ) != null ) && ( ticket.getDateCreate( ) != null ) )
        {
            long diffDelaiReponse = ticket.getDateClose( ).getTime( ) - ticket.getDateCreate( ).getTime( );
            long delaiReponse = TimeUnit.DAYS.convert( diffDelaiReponse, TimeUnit.MILLISECONDS );
            ticket.setDelaiReponse( delaiReponse );
        }

        Unit unit = unitMap.get( daoUtil.getInt( "id_unit" ) );
        if ( unit != null )
        {
            ticket.setEntite( unit.getLabel( ) );
        }

        ticket.setCanal( daoUtil.getString( "label" ) );
        ticket.setStatut( daoUtil.getString( "name" ) );
        ticket.setArrondissement( daoUtil.getInt( "arrondissement" ) );

        return ticket;
    }

    /**
     * Gets the parent category.
     *
     * @param category
     *            the category
     * @param catMap
     *            the cat map
     * @param type
     *            the type
     * @return the parent category
     */
    private static String getParentCategory( TicketCategory category, Map<Integer, TicketCategory> catMap, int type )
    {
        if ( category != null )
        {
            if ( category.getCategoryType( ).getId( ) == type )
            {
                return category.getLabel( );
            } else
            {
                TicketCategory ticketCategory = catMap.get( category.getIdParent( ) );
                if ( ticketCategory.getCategoryType( ).getId( ) == type )
                {
                    return ticketCategory.getLabel( );
                } else
                {
                    return getParentCategory( ticketCategory, catMap, type );
                }
            }
        }
        return null;
    }

    /**
     * Fill ticket list.
     *
     * @param ids the ids
     * @param ticketDataObjectList the ticket data object list
     * @param plugin the plugin
     * @return the list
     */
    private List<DataObject> fillTicketList( List<Integer> ids, List<TicketDataObject> ticketDataObjectList, Plugin plugin )
    {
        List<DataObject> ticketList = new ArrayList<>( );

        AppLogService.info( "Elastic Search selectAll - Nb ticket : " + ids.size( ) );

        ResourceWorkflowHistoryDAO rwhDAO = new ResourceWorkflowHistoryDAO( );

        if( !ids.isEmpty( ) )
        {
            List<DateActionWorkflow> listDateActionWorkflow = rwhDAO.getCompleteResourceWorkflowHistory( ids, plugin );

            for ( TicketDataObject ticket : ticketDataObjectList )
            {
                Optional<DateActionWorkflow> optDaw = listDateActionWorkflow.stream( )
                        .filter( dateActionWorkflow -> dateActionWorkflow.getIdTicket( ) == ticket.getIdTicket( ) ).findFirst( );
                if ( optDaw.isPresent( ) )
                {
                    DateActionWorkflow daw = optDaw.get( );
                    ticket.setId( String.valueOf( daw.getIdTicket( ) ));
                    ticket.setDateAssignation( daw.getDateAssignment( ) );
                    ticket.setDateLastReAssignmentN1toN2( daw.getDateLastReAssignmentN1toN2( ) );
                    ticket.setDateLastClimb( daw.getDateLastClimbToN3( ) );
                    ticket.setDateLastResponseN3( daw.getDateLastResponseN3( ) );
                    ticket.setDateLastSollicitationATCM( daw.getDateLastSollicitationATCM( ) );
                    ticket.setDateLastResponseATCM( daw.getDateLastResponseATCM( ) );
                    ticket.setDateLastAdditionalRequest( daw.getDateLastAdditionalRequest( ) );
                    ticket.setDateLastAdditionalRequestResponse( daw.getDateLastAdditionalRequestResponse( ) );
                    ticket.setDateLastAssignmentN2toN1( daw.getDateLastAssignmentN2toN1( ) );
                    ticket.setDelayPriseEnCharge( daw.getTimeSupport( new Timestamp( ticket.getDateCreate( ).getTime( ) ) ) );
                    ticket.setDelayReassignation( daw.getDelayReassignationN1toN2( new Timestamp( ticket.getDateCreate( ).getTime( ) ) ) );
                    ticket.setDelayN3( daw.getDelayATCM( ) );
                    ticket.setDelayATCM( daw.getDelayATCM( ) );
                    ticket.setDelayComplement( daw.getDelayComplement( ) );
                }
                ticketList.add( ticket );
            }
        }

        return ticketList;
    }

    public List<String> selectIdTicketsList( Plugin plugin )
    {
        return selectAll( plugin ).stream( ).map( DataObject::getId ).collect( Collectors.toList( ) );

    }

}
