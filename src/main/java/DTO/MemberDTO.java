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

}
