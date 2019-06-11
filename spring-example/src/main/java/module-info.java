/**
 * Copyright 2019 Deere & Company
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

// Unpublished Work (c) 2019 Deere & Company

open module com.deere.isg.worktracker.spring.example {
    requires  logback.ext.spring;
    requires slf4j.api;
    requires jcl.over.slf4j;
    requires jul.to.slf4j;
    requires log4j.over.slf4j;
    requires spring.webmvc;
    requires com.deere.isg.worktracker.spring;
    requires com.fasterxml.jackson.databind;
    requires javax.servlet.api;
    requires spring.context;
    requires spring.web;
    requires spring.beans;
    exports com.deere.example;
}