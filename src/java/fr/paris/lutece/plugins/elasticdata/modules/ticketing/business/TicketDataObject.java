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

import java.util.Date;

import fr.paris.lutece.plugins.elasticdata.business.DataObject;

/**
 * SignalementDataObject
 */
public class TicketDataObject implements DataObject
{

    private String _strDomaine;
    private String _strThematique;
    private Date   _dateCreate;
    private Date   _dateClose;
    private String _strEntite;
    private long   _nAnciennete;
    private long   _nDelaiReponse;

    @Override
    public String getTimestamp( )
    {
        return String.valueOf( new Date( ).getTime( ) );
    }

    /**
     * @return the _strDomaine
     */
    public String getDomaine( )
    {
        return _strDomaine;
    }

    /**
     * @param _strDomaine
     *            the _strDomaine to set
     */
    public void setDomaine( String _strDomaine )
    {
        this._strDomaine = _strDomaine;
    }

    /**
     * @return the _strThematique
     */
    public String getThematique( )
    {
        return _strThematique;
    }

    /**
     * @param _strThematique
     *            the _strThematique to set
     */
    public void setThematique( String _strThematique )
    {
        this._strThematique = _strThematique;
    }

    /**
     * @return the _dateCreate
     */
    public Date getDateCreate( )
    {
        return _dateCreate;
    }

    /**
     * @param _dateCreate
     *            the _dateCreate to set
     */
    public void setDateCreate( Date _dateCreate )
    {
        this._dateCreate = _dateCreate;
    }

    /**
     * @return the _strEntite
     */
    public String getEntite( )
    {
        return _strEntite;
    }

    /**
     * @param _strEntite
     *            the _strEntite to set
     */
    public void setEntite( String _strEntite )
    {
        this._strEntite = _strEntite;
    }

    /**
     * @return the _dateClose
     */
    public Date getDateClose( )
    {
        return _dateClose;
    }

    /**
     * @param _dateClose
     *            the _dateClose to set
     */
    public void setDateClose( Date _dateClose )
    {
        this._dateClose = _dateClose;
    }

    /**
     * @return the _nAnciennete
     */
    public long getAnciennete( )
    {
        return _nAnciennete;
    }

    /**
     * @param _nAnciennete
     *            the _nAnciennete to set
     */
    public void setAnciennete( long _nAnciennete )
    {
        this._nAnciennete = _nAnciennete;
    }

    /**
     * @return the _nDelaiReponse
     */
    public long getDelaiReponse( )
    {
        return _nDelaiReponse;
    }

    /**
     * @param _nDelaiReponse
     *            the _nDelaiReponse to set
     */
    public void setDelaiReponse( long _nDelaiReponse )
    {
        this._nDelaiReponse = _nDelaiReponse;
    }

}
