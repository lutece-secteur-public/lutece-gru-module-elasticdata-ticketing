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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.plugins.ticketing.business.category.TicketCategory;
import fr.paris.lutece.plugins.ticketing.business.category.TicketCategoryHome;
import fr.paris.lutece.plugins.unittree.business.unit.Unit;
import fr.paris.lutece.plugins.unittree.business.unit.UnitHome;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * TicketDAO
 */
public class TicketDAO
{

    private static final String SQL_QUERY_SELECTALL = "SELECT id_ticket_category, date_create, date_close, id_unit FROM ticketing_ticket";

    public Collection<DataObject> selectAll( Plugin plugin )
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

        List<DataObject> ticketList = new ArrayList<DataObject>( );

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECTALL, plugin );

        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
            TicketDataObject ticket = dataToTicket( daoUtil, catMap, unitMap );

            ticketList.add( ticket );
        }

        daoUtil.free( );

        return ticketList;
    }

    private static TicketDataObject dataToTicket( DAOUtil daoUtil, Map<Integer, TicketCategory> catMap, Map<Integer, Unit> unitMap )
    {
        TicketDataObject ticket = new TicketDataObject( );
        TicketCategory category = catMap.get( daoUtil.getInt( "id_ticket_category" ) );
        ticket.setThematique( getParentCategory( category, catMap, 2 ) );
        ticket.setDomaine( getParentCategory( category, catMap, 1 ) );
        ticket.setDateCreate( daoUtil.getDate( "date_create" ) );
        ticket.setDateClose( daoUtil.getDate( "date_close" ) );
        long diff = new Date( ).getTime( ) - ticket.getDateCreate( ).getTime( );
        long anciennete = TimeUnit.DAYS.convert( diff, TimeUnit.MILLISECONDS );
        ticket.setAnciennete( anciennete );

        if ( ticket.getDateClose( ) != null )
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

        return ticket;
    }

    private static String getParentCategory( TicketCategory category, Map<Integer, TicketCategory> catMap, int type )
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

}
