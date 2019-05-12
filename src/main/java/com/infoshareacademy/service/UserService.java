package com.infoshareacademy.service;


import com.infoshareacademy.dao.ComputerDao;
import com.infoshareacademy.dao.StudentDao;
import com.infoshareacademy.model.Computer;
import com.infoshareacademy.model.Student;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@Path("/")
public class UserService {

    @Context
    private UriInfo uriInfo;

    @Inject
    private StudentDao studentDao;

    @Inject
    private ComputerDao computerDao;

    public UserService() {}

    @GET
    @Path("/api/students")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {

        List<Student> studentList = studentDao.findAll();

        if (studentList.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(studentList).build();
    }

    @GET
    @Path("/api/students/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentsByName(@PathParam("name") String name) {

        List<Student> studentList = studentDao.findAll().stream()
                .filter(student -> student.getName().matches(name))
                .collect(Collectors.toList());

        if (studentList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(studentList).build();
    }

    @POST
    @Path("/api/computers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComputer(Computer computer) {
        String name = computer.getName();
        String operatingSystem = computer.getOperatingSystem();

        Computer c1 = new Computer(name, operatingSystem);
        c1.getId();
        computerDao.save(c1);

        List<Computer> computerList = computerDao.findAll();

        if (computerList.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(computerList).build();
    }

    @DELETE
    @Path("/api/computers/{id}")
    public Response deleteComputer(@PathParam("id") Long id) {

        List<Computer> filteredList = computerDao.findAll().stream()
                .filter(computer -> computer.getId().equals(id))
                .collect(Collectors.toList());


        if (!filteredList.isEmpty()) {
            computerDao.delete(id);
            return Response.status(200).build();
        } else {
            return Response.status(404).build();
        }
    }

}
