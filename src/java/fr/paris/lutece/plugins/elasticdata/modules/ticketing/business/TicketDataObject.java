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
 * TicketDataObject.
 */
public class TicketDataObject implements DataObject
{

    /** The str domaine. */
    private String  _strDomaine;

    /** The str thematique. */
    private String  _strThematique;

    /** The date create. */
    private Date    _dateCreate;

    /** The date close. */
    private Date    _dateClose;

    /** The str entite. */
    private String  _strEntite;

    /** The n anciennete. */
    private long    _nAnciennete;

    /** The n delai reponse. */
    private long    _nDelaiReponse;

    /** The str guid. */
    private String  _strGuid;

    private Integer _idTicket;

    private String  _strSousThematique;

    private String  _strStatut;

    private String  _strCanal;

    private String  _strLocalisation;

    private Date    _dDateAssignment;

    private Date    _dDateLastReAssignmentN1toN2;

    private Date    _dDateLastClimb;

    private Date    _dDateLastResponseN3;

    private Date    _dDateLastSollicitationATCM;

    private Date    _dDateLastResponseATCM;

    private Date    _dDateLastAdditionalRequest;

    private Date    _dDateLastAdditionalRequestResponse;

    private Date    _dDateLastAssignmentN2toN1;

    private String  _strDelayPriseEnCharge;

    private String  _strDelayReassignation;

    private String  _strDelayN3;

    private String  _strDelayATCM;

    private String  _strDelayComplement;

    private Integer _nArrondissement;

    private String _strId;
    
    private String _strHeureCreation;
    
    private String _strAgentAssigne;

    private String _strReference;

    private String  _strIsVsp;

    private String  _strIsSignalement;

    private String  _strVspRulesList;

    private String  _strCompletedNameUserBO;

    /*
     * (non-Javadoc)
     *
     * @see fr.paris.lutece.plugins.elasticdata.business.DataObject#getTimestamp()
     */
    @Override
    public String getTimestamp( )
    {
        return String.valueOf( new Date( ).getTime( ) );
    }

    /**
     * Gets the domaine.
     *
     * @return the _strDomaine
     */
    public String getDomaine( )
    {
        return _strDomaine;
    }

    /**
     * Sets the domaine.
     *
     * @param _strDomaine
     *            the _strDomaine to set
     */
    public void setDomaine( String _strDomaine )
    {
        this._strDomaine = _strDomaine;
    }

    /**
     * Gets the thematique.
     *
     * @return the _strThematique
     */
    public String getThematique( )
    {
        return _strThematique;
    }

    /**
     * Sets the thematique.
     *
     * @param _strThematique
     *            the _strThematique to set
     */
    public void setThematique( String _strThematique )
    {
        this._strThematique = _strThematique;
    }

    /**
     * Gets the date create.
     *
     * @return the _dateCreate
     */
    public Date getDateCreate( )
    {
        return _dateCreate;
    }

    /**
     * Sets the date create.
     *
     * @param _dateCreate
     *            the _dateCreate to set
     */
    public void setDateCreate( Date _dateCreate )
    {
        this._dateCreate = _dateCreate;
    }

    /**
     * Gets the entite.
     *
     * @return the _strEntite
     */
    public String getEntite( )
    {
        return _strEntite;
    }

    /**
     * Sets the entite.
     *
     * @param _strEntite
     *            the _strEntite to set
     */
    public void setEntite( String _strEntite )
    {
        this._strEntite = _strEntite;
    }

    /**
     * Gets the date close.
     *
     * @return the _dateClose
     */
    public Date getDateClose( )
    {
        return _dateClose;
    }

    /**
     * Sets the date close.
     *
     * @param _dateClose
     *            the _dateClose to set
     */
    public void setDateClose( Date _dateClose )
    {
        this._dateClose = _dateClose;
    }

    /**
     * Gets the anciennete.
     *
     * @return the _nAnciennete
     */
    public long getAnciennete( )
    {
        return _nAnciennete;
    }

    /**
     * Sets the anciennete.
     *
     * @param _nAnciennete
     *            the _nAnciennete to set
     */
    public void setAnciennete( long _nAnciennete )
    {
        this._nAnciennete = _nAnciennete;
    }

    /**
     * Gets the delai reponse.
     *
     * @return the _nDelaiReponse
     */
    public long getDelaiReponse( )
    {
        return _nDelaiReponse;
    }

    /**
     * Sets the delai reponse.
     *
     * @param _nDelaiReponse
     *            the _nDelaiReponse to set
     */
    public void setDelaiReponse( long _nDelaiReponse )
    {
        this._nDelaiReponse = _nDelaiReponse;
    }

    /**
     * Gets the guid.
     *
     * @return the _strGuid
     */
    public String getGuid( )
    {
        return _strGuid;
    }

    /**
     * Sets the guid.
     *
     * @param _strGuid
     *            the _strGuid to set
     */
    public void setGuid( String _strGuid )
    {
        this._strGuid = _strGuid;
    }

    public Integer getIdTicket( )
    {
        return _idTicket;
    }

    public void setIdTicket( Integer idTicket )
    {
        _idTicket = idTicket;
    }

    public String getSousThematique( )
    {
        return _strSousThematique;
    }

    public void setSousThematique( String _strSousThematique )
    {
        this._strSousThematique = _strSousThematique;
    }

    public String getStatut( )
    {
        return _strStatut;
    }

    public void setStatut( String _strStatut )
    {
        this._strStatut = _strStatut;
    }

    public String getCanal( )
    {
        return _strCanal;
    }

    public void setCanal( String _strCanal )
    {
        this._strCanal = _strCanal;
    }

    public String getLocalisation( )
    {
        return _strLocalisation;
    }

    public void setLocalisation( String _strLocalisation )
    {
        this._strLocalisation = _strLocalisation;
    }

    public Date getDateAssignment( )
    {
        return _dDateAssignment;
    }

    public void setDateAssignation( Date dateAssignment )
    {
        _dDateAssignment = dateAssignment;
    }

    public Date getDateLastReAssignmentN1toN2( )
    {
        return _dDateLastReAssignmentN1toN2;
    }

    public Date getDateLastClimb( )
    {
        return _dDateLastClimb;
    }

    public Date getDateLastResponseN3( )
    {
        return _dDateLastResponseN3;
    }

    public Date getDateLastSollicitationATCM( )
    {
        return _dDateLastSollicitationATCM;
    }

    public Date getDateLastResponseATCM( )
    {
        return _dDateLastResponseATCM;
    }

    public Date getDateLastAdditionalRequest( )
    {
        return _dDateLastAdditionalRequest;
    }

    public Date getDateLastAdditionalRequestResponse( )
    {
        return _dDateLastAdditionalRequestResponse;
    }

    public Date getDateLastAssignmentN2toN1( )
    {
        return _dDateLastAssignmentN2toN1;
    }

    public String getDelayPriseEnCharge( )
    {
        return _strDelayPriseEnCharge;
    }

    public String getDelayReassignation( )
    {
        return _strDelayReassignation;
    }

    public String getDelayN3( )
    {
        return _strDelayN3;
    }

    public String getDelayATCM( )
    {
        return _strDelayATCM;
    }

    public String getDelayComplement( )
    {
        return _strDelayComplement;
    }

    public void setDateLastReAssignmentN1toN2( Date dateLastReAssignmentN1toN2 )
    {
        _dDateLastReAssignmentN1toN2 = dateLastReAssignmentN1toN2;
    }

    public void setDateLastClimb( Date dateLastClimb )
    {
        _dDateLastClimb = dateLastClimb;
    }

    public void setDateLastResponseN3( Date dateLastResponseN3 )
    {
        _dDateLastResponseN3 = dateLastResponseN3;
    }

    public void setDateLastSollicitationATCM( Date dateLastSollicitationATCM )
    {
        _dDateLastSollicitationATCM = dateLastSollicitationATCM;
    }

    public void setDateLastResponseATCM( Date dateLastResponseATCM )
    {
        _dDateLastResponseATCM = dateLastResponseATCM;
    }

    public void setDateLastAdditionalRequest( Date dateLastAdditionalRequest )
    {
        _dDateLastAdditionalRequest = dateLastAdditionalRequest;
    }

    public void setDateLastAdditionalRequestResponse( Date dateLastAdditionalRequestResponse )
    {
        _dDateLastAdditionalRequestResponse = dateLastAdditionalRequestResponse;
    }

    public void setDateLastAssignmentN2toN1( Date dateLastAssignmentN2toN1 )
    {
        _dDateLastAssignmentN2toN1 = dateLastAssignmentN2toN1;
    }

    public void setDelayPriseEnCharge( String delayPriseEnCharge )
    {
        _strDelayPriseEnCharge = delayPriseEnCharge;
    }

    public void setDelayReassignation( String delayReassignation )
    {
        _strDelayReassignation = delayReassignation;
    }

    public void setDelayN3( String delayN3 )
    {
        _strDelayN3 = delayN3;
    }

    public void setDelayATCM( String delayATCM )
    {
        _strDelayATCM = delayATCM;
    }

    public void setDelayComplement( String delayComplement )
    {
        _strDelayComplement = delayComplement;
    }

    public Integer getArrondissement( )
    {
        return _nArrondissement;
    }

    public void setArrondissement( Integer arrondissement )
    {
        _nArrondissement = arrondissement;
    }

    @Override
    public String getId( )
    {
        return _strId;
    }

    public void setId( String strId )
    {
        _strId = strId;
    }
    
    public String getHeureCreation( )
    {
        return _strHeureCreation;
    }

    public void setHeureCreation( String strHeureCreation )
    {
        _strHeureCreation = strHeureCreation;
    }

    public String getAgentAssigne( )
    {
        return _strAgentAssigne;
    }

    public void setAgentAssigne( String strAgentAssigne )
    {
        _strAgentAssigne = strAgentAssigne;
    }


    public String getReference( )
    {
        return _strReference;
    }

    public void setReference( String strReference )
    {
        _strReference = strReference;
    }

    /**
     * @return the _strIsVsp
     */
    public String getIsVsp( )
    {
        return _strIsVsp;
    }

    /**
     * @param strIsVsp
     *            the _strIsVsp to set
     */
    public void setIsVsp( String strIsVsp )
    {
        _strIsVsp = strIsVsp;
    }

    /**
     * @return the _strIsSignalement
     */
    public String getIsSignalement( )
    {
        return _strIsSignalement;
    }

    /**
     * @param strIsSignalement
     *            the _strIsSignalement to set
     */
    public void setIsSignalement( String isSignalement )
    {
        _strIsSignalement = isSignalement;
    }

    /**
     * @return the _strVspRulesList
     */
    public String getVspRulesList( )
    {
        return _strVspRulesList;
    }

    /**
     * @param strVspRulesList
     *            the _strVspRulesList to set
     */
    public void setVspRulesList( String strVspRulesList )
    {
        _strVspRulesList = strVspRulesList;
    }

    /**
     * @return the _strCompletedNameUserBO
     */
    public String getCompletedNameUserBO( )
    {
        return _strCompletedNameUserBO;
    }

    /**
     * @param strCompletedNameUserBO
     *            the _strCompletedNameUserBO to set
     */
    public void setCompletedNameUserBO( String strCompletedNameUserBO )
    {
        _strCompletedNameUserBO = strCompletedNameUserBO;
    }

}
