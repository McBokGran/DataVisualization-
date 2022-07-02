import React, { useState, useEffect } from "react";
import "./navbar.css";
import * as Icon from "react-feather";
import logo from "../logo.png";

const Navbar = () => {
  return (
    <React.Fragment>
      <header className="navbar navbar-light sticky-top bg-light flex-md-nowrap p-0 shadow">
        <a className="navbar-brand col-md-3 col-lg-2 me-0 px-3" href="#">
          <img
            src={logo}
            height="25"
            className="logo d-inline-block align-top"
            alt=""
          ></img>
          {/* Company name */}
        </a>
        <button
          className="navbar-toggler position-absolute d-md-none collapsed"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#sidebarMenu"
          aria-controls="sidebarMenu"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <input
          className="form-control form-control-dark w-100"
          type="text"
          placeholder=""
          aria-label="Search"
        ></input>
        <div className="navbar-nav">
          <div className="nav-item text-nowrap">
            <a className="nav-link px-3" href="#">
              _________
            </a>
          </div>
        </div>
      </header>
    </React.Fragment>
  );
};

export default Navbar;
