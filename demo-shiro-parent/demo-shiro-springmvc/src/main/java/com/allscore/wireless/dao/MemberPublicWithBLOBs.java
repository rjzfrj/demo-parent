package com.allscore.wireless.dao;

public class MemberPublicWithBLOBs extends MemberPublic {
    /**
     *null
     */
    private byte[] BUSINESS_LICENSE;

    /**
     *null
     */
    private byte[] TAX_REGISTRATION;

    /**
     *null
     */
    private byte[] LEGAL_IDCARD;

    /**
     *null
     */
    private byte[] ORGANIZATION;

    /**
     *null
     */
    private byte[] OPENING_PERMIT;

    /**
     *null
     */
    private byte[] SHOP_LOGO;

    /**
     *null
     */
    public byte[] getBUSINESS_LICENSE() {
        return BUSINESS_LICENSE;
    }

    /**
     *null
     */
    public void setBUSINESS_LICENSE(byte[] BUSINESS_LICENSE) {
        this.BUSINESS_LICENSE = BUSINESS_LICENSE;
    }

    /**
     *null
     */
    public byte[] getTAX_REGISTRATION() {
        return TAX_REGISTRATION;
    }

    /**
     *null
     */
    public void setTAX_REGISTRATION(byte[] TAX_REGISTRATION) {
        this.TAX_REGISTRATION = TAX_REGISTRATION;
    }

    /**
     *null
     */
    public byte[] getLEGAL_IDCARD() {
        return LEGAL_IDCARD;
    }

    /**
     *null
     */
    public void setLEGAL_IDCARD(byte[] LEGAL_IDCARD) {
        this.LEGAL_IDCARD = LEGAL_IDCARD;
    }

    /**
     *null
     */
    public byte[] getORGANIZATION() {
        return ORGANIZATION;
    }

    /**
     *null
     */
    public void setORGANIZATION(byte[] ORGANIZATION) {
        this.ORGANIZATION = ORGANIZATION;
    }

    /**
     *null
     */
    public byte[] getOPENING_PERMIT() {
        return OPENING_PERMIT;
    }

    /**
     *null
     */
    public void setOPENING_PERMIT(byte[] OPENING_PERMIT) {
        this.OPENING_PERMIT = OPENING_PERMIT;
    }

    /**
     *null
     */
    public byte[] getSHOP_LOGO() {
        return SHOP_LOGO;
    }

    /**
     *null
     */
    public void setSHOP_LOGO(byte[] SHOP_LOGO) {
        this.SHOP_LOGO = SHOP_LOGO;
    }
}