/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tricuong.lab231.carrental.object;

/**
 *
 * @author TriCuong
 */
public class RegistrationError {
    private String emailEmpty;
    private String phoneEmpty;
    private String nameEmpty;
    private String addressEmpty;
    private String passwordErr;
    private String emailFormat;
    private String emailExisted;
    private String phoneFormat;

    public RegistrationError() {
    }

    public String getPasswordErr() {
        return passwordErr;
    }

    public void setPasswordErr(String passwordErr) {
        this.passwordErr = passwordErr;
    }

    public RegistrationError(String emailEmpty, String phoneEmpty, String nameEmpty, String addressEmpty, String passwordErr, String emailFormat, String emailExisted, String phoneFormat) {
        this.emailEmpty = emailEmpty;
        this.phoneEmpty = phoneEmpty;
        this.nameEmpty = nameEmpty;
        this.addressEmpty = addressEmpty;
        this.passwordErr = passwordErr;
        this.emailFormat = emailFormat;
        this.emailExisted = emailExisted;
        this.phoneFormat = phoneFormat;
    }


    /**
     * @return the emailEmpty
     */
    public String getEmailEmpty() {
        return emailEmpty;
    }

    /**
     * @param emailEmpty the emailEmpty to set
     */
    public void setEmailEmpty(String emailEmpty) {
        this.emailEmpty = emailEmpty;
    }

    /**
     * @return the phoneEmpty
     */
    public String getPhoneEmpty() {
        return phoneEmpty;
    }

    /**
     * @param phoneEmpty the phoneEmpty to set
     */
    public void setPhoneEmpty(String phoneEmpty) {
        this.phoneEmpty = phoneEmpty;
    }

    /**
     * @return the nameEmpty
     */
    public String getNameEmpty() {
        return nameEmpty;
    }

    /**
     * @param nameEmpty the nameEmpty to set
     */
    public void setNameEmpty(String nameEmpty) {
        this.nameEmpty = nameEmpty;
    }

    /**
     * @return the addressEmpty
     */
    public String getAddressEmpty() {
        return addressEmpty;
    }

    /**
     * @param addressEmpty the addressEmpty to set
     */
    public void setAddressEmpty(String addressEmpty) {
        this.addressEmpty = addressEmpty;
    }

    /**
     * @return the emailFormat
     */
    public String getEmailFormat() {
        return emailFormat;
    }

    /**
     * @param emailFormat the emailFormat to set
     */
    public void setEmailFormat(String emailFormat) {
        this.emailFormat = emailFormat;
    }

    /**
     * @return the emailExisted
     */
    public String getEmailExisted() {
        return emailExisted;
    }

    /**
     * @param emailExisted the emailExisted to set
     */
    public void setEmailExisted(String emailExisted) {
        this.emailExisted = emailExisted;
    }

    /**
     * @return the phoneFormat
     */
    public String getPhoneFormat() {
        return phoneFormat;
    }

    /**
     * @param phoneFormat the phoneFormat to set
     */
    public void setPhoneFormat(String phoneFormat) {
        this.phoneFormat = phoneFormat;
    }
    
}
