/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.implementation;

import api.interfaces.LabApi;
import dao.Implementation.lab.LabImpl;
import java.sql.Date;
import java.util.ArrayList;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojos.LabPojo;
import pojos.ResponsePojo;

/**
 *
 * @author Aya
 */
@Path("/lab")
public class LabApiImplementation implements LabApi {

    @POST
    @Path("/insert")
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponsePojo insertData(@FormParam("lab_name_en") String lab_name_en, @FormParam("lab_open_hour") String lab_open_hour, @FormParam("lab_close_hour") String lab_close_hour, @FormParam("lab_latitude") String lab_latitude, @FormParam("lab_longitude") String lab_longitude, @FormParam("lab_address") String lab_address, @FormParam("lab_start_date") String lab_start_date, @FormParam("lab_end_date") String lab_end_date, @FormParam("lab_rate") int lab_rate, @FormParam("lab_ceo") String lab_ceo, @FormParam("lab_name_ar") String lab_name_ar, @FormParam("phone1") String phone1, @FormParam("phone2") String phone2, @FormParam("phone3") String phone3, @FormParam("c1") String special1, @FormParam("c2") String special2, @FormParam("c3") String special3, @FormParam("c4") String special4, @FormParam("c5") String special5, @FormParam("c6") String special6, @FormParam("c7") String special7, @FormParam("c8") String special8) {
        ResponsePojo response = new ResponsePojo();
        ArrayList<String> phones = new ArrayList();
        ArrayList<String> specializations = new ArrayList();
        LabPojo lab = new LabPojo();
        LabImpl labObj = new LabImpl();

        double mylongitude = Double.parseDouble(lab_longitude);
        double mylatitude = Double.parseDouble(lab_latitude);
        Date start = null, end = null;
        start = java.sql.Date.valueOf(lab_start_date);
        end = java.sql.Date.valueOf(lab_end_date);

        if (!phone1.isEmpty()) {
            phones.add(phone1);
        }
        if (!phone2.isEmpty()) {
            phones.add(phone2);
        }
        if (!phone3.isEmpty()) {
            phones.add(phone3);
        }

        if (special1 != null) {
            specializations.add(special1);
        }
        if (special2 != null) {
            specializations.add(special2);
        }
        if (special3 != null) {
            specializations.add(special3);
        }
        if (special4 != null) {
            specializations.add(special4);
        }
        if (special5 != null) {
            specializations.add(special5);
        }
        if (special6 != null) {
            specializations.add(special6);
        }
        if (special7 != null) {
            specializations.add(special7);
        }
        if (special8 != null) {
            specializations.add(special8);
        }

        lab.setLabAddress(lab_address);
        lab.setLabCeo(lab_ceo);
        lab.setLabRate(lab_rate);
        lab.setLabLatitude(mylatitude);
        lab.setLabLongitude(mylongitude);
        lab.setLabStartDate(start);
        lab.setLabEndDate(end);
        lab.setLabNameAr(lab_name_ar);
        lab.setLabNameEn(lab_name_en);
        lab.setLabOpenHour(lab_open_hour);
        lab.setLabCloseHour(lab_close_hour);
        lab.setMedicalTypeMedicalTypeId(4);
        lab.setLabPhones(phones);
        lab.setLabSpecializations(specializations);

        boolean result = labObj.addLab(lab);

        if (result == true) {
            response.setStatus(true);
            response.setMessage("Lab Added Successfully");
            response.setError("No Error");
            return response;
        } else {
            response.setStatus(false);
            response.setMessage("Lab Failed to be added");
            response.setError(" Error");
            return response;

        }

    }

    @DELETE
    ///{type_id}
    @Path("/delete/{lab_id}")
    public ResponsePojo deleteLab(@PathParam("lab_id") int lab_id) {
        //,@PathParam("type_id")int type_id
        ResponsePojo response = new ResponsePojo();
        LabImpl labObj = new LabImpl();

        boolean result = labObj.deleteLab(lab_id);

        if (result == true) {
            response.setStatus(true);
            response.setMessage("lab deleted Successfully");
            response.setError("No Error");
            return response;
        } else {
            response.setStatus(false);
            response.setMessage("lab deletion failed");
            response.setError(" Error");
            return response;

        }
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LabPojo> getLab() {

        LabImpl labObj = new LabImpl();
        ArrayList<LabPojo> labs = new ArrayList();
        labs = labObj.getAllLabs();
        return labs;
    }
}