import React, { useState, useEffect } from "react";
import "./sidebar.css";
import SidebarItem from "./SidebarItem";
import * as Icon from "react-feather";

const Sidebar = () => {
  return (
    <React.Fragment>
      <nav
        id="sidebarMenu"
        className="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
      >
        <div className="position-sticky pt-3">
          <ul className="nav flex-column">
            <SidebarItem
              icon={<Icon.Home color="#000000" size="17" />}
              title="Dashboard"
              link="/dashboard"
            />
            <SidebarItem
              icon={<Icon.File color="#000000" size="17" />}
              title="Media Categories"
              link="/mediacategories"
            />
            <SidebarItem
              icon={<Icon.Printer color="#000000" size="17" />}
              title="Print Modes"
              link="/printmodes"
            />
            <SidebarItem
              icon={<Icon.Droplet color="#000000" size="17" />}
              title="Ink Usage"
              link="/inkusage"
            />
            

              <SidebarItem
              icon={<Icon.FileText color="#000000" size="17" />}
              title="Media types per machine"
              link="/mediatypespermachine"
            />
            <SidebarItem
              icon={<Icon.FileText color="#000000" size="17" />}
              title="Top 10 machines"
              link="/top10"
            />
          </ul>

          <h6 className="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
            <span>Saved reports</span>
            <a className="link-secondary" href="#" aria-label="Add a new report">
              <span>
                <Icon.PlusCircle color="#000000" size="17" />
              </span>
            </a>
          </h6>
          <ul className="nav flex-column mb-2">
            <SidebarItem
              icon={<Icon.BarChart2 color="#000000" size="17" />}
              title="Current Month"
            />
            <SidebarItem
              icon={<Icon.BarChart2 color="#000000" size="17" />}
              title="Last quarter"
            />
            <SidebarItem
              icon={<Icon.BarChart2 color="#000000" size="17" />}
              title="Last 4 months"
            />
            <SidebarItem
              icon={<Icon.BarChart2 color="#000000" size="17" />}
              title="Weekly Media"
            />
          </ul>
        </div>
      </nav>
    </React.Fragment>
  );
};

export default Sidebar;
