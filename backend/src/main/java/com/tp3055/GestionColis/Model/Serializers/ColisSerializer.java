package com.tp3055.GestionColis.Model.Serializers;

import com.tp3055.GestionColis.Model.Entity.Colis;
import com.tp3055.GestionColis.Model.Entity.Expedition;
import com.tp3055.GestionColis.Model.Entity.Retrait;
import com.tp3055.GestionColis.Model.Entity.State;

public class ColisSerializer {
    private Long id;
    private String description;
    private String clientCNI;// CNI du client
    private String clientName; // nom du client
    private String recieverName; // nom du destinataire
    private String recieverPhone; // numero telephone du destinataire du colis
    private String fromTown; // de la ville
    private String toTown; // a la ville
    private State state; // etat du colis
    private String expedtionCode;

    private Expedition send;
    private Retrait withdraw;

    public ColisSerializer(Colis colis) {
        this.id = colis.getId();
        this.description = colis.getDescription();
        this.clientCNI = colis.getClientCNI();
        this.clientName = colis.getClientName();
        this.recieverName = colis.getRecieverName();
        this.recieverPhone = colis.getRecieverPhone();
        this.fromTown = colis.getFromTown();
        this.toTown = colis.getToTown();
        this.state = colis.getState();
        this.expedtionCode = colis.getExpedtionCode();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientCNI() {
        return clientCNI;
    }

    public void setClientCNI(String clientCNI) {
        this.clientCNI = clientCNI;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getRecieverPhone() {
        return recieverPhone;
    }

    public void setRecieverPhone(String recieverPhone) {
        this.recieverPhone = recieverPhone;
    }

    public String getFromTown() {
        return fromTown;
    }

    public void setFromTown(String fromTown) {
        this.fromTown = fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public void setToTown(String toTown) {
        this.toTown = toTown;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getExpedtionCode() {
        return expedtionCode;
    }

    public void setExpedtionCode(String expedtionCode) {
        this.expedtionCode = expedtionCode;
    }

    public Expedition getSend() {
        return send;
    }

    public void setSend(Expedition send) {
        this.send = send;
    }

    public Retrait getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Retrait withdraw) {
        this.withdraw = withdraw;
    }

}
