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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import fr.paris.lutece.plugins.elasticdata.business.AbstractDataSource;
import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.portal.service.datastore.DatastoreService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

/**
 * TicketDataSource
 */
public class TicketDataSource extends AbstractDataSource
{

    /** The Constant PLUGIN_NAME. */
    private static final String PLUGIN_NAME = "elasticdata-ticketing";
    private static final String KEY_DATE_LAST_INDEXATION = "ticketing.configuration.date.last.indexation";

    /** The plugin. */
    private static Plugin       _plugin     = PluginService.getPlugin( PLUGIN_NAME );

    /**
     * {@inheritDoc }
     */
    public Collection<DataObject> fetchDataObjects()
    {
        Date date = new Date( );
        Timestamp currentTimestamp = new Timestamp(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = sdf.format( currentTimestamp );

        TicketDAO dao = new TicketDAO( );

        List<DataObject> ticketsToIndex = dao.selectAll( _plugin );

        DatastoreService.setDataValue( KEY_DATE_LAST_INDEXATION, currentDate );

        return ticketsToIndex;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isLocalizable( )
    {
        return true;
    }

    @Override
    public List<String> getIdDataObjects( )
    {
        TicketDAO dao = new TicketDAO( );
        return dao.selectIdTicketsList( _plugin );
    }


}
