/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import entities.Member;


public class MemberDTO {

    private long id;
    private String name;
    private int studentId;
    private String favoriteSeries;

    public MemberDTO(Member member){

        this.id = member.getId();
        this.name = member.getName();
        this.studentId = member.getStudentId();
        this.favoriteSeries = member.getFavoriteSeries();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFavoriteSeries() {
        return favoriteSeries;
    }

    public void setFavoriteSeries(String favoriteSeries) {
        this.favoriteSeries = favoriteSeries;
    }
    
    
    
    @Override
    public String toString() {
        return "MemberDTO{" + "id=" + id + ", name=" + name + ", studentId=" + studentId + ", favoriteSeries=" + favoriteSeries + '}';
    }
}
