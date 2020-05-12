import React, { Component } from "react";
import { Collapse, Navbar, NavbarBrand, Nav, NavItem, NavLink } from "reactstrap";

export default class NaviComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { isOpen: false };
  }
  render() {
    return (
      <div className="NaviComponent">
        <Navbar color="dark" expand="md" className="navbar navbar-dark bg-dark">
          <NavbarBrand
            href="/"
            style={{ height: 50, fontWeight: "bold", fontSize: 25 }}
          >
            TO-DO App
          </NavbarBrand>
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto">
              <NavItem>
                <NavLink
                  href="https://github.com/ustundag"
                  style={{ color: "white", textDecoration: "none" }}
                >
                  GitHub
                </NavLink>
              </NavItem>
            </Nav>
          </Collapse>
        </Navbar>
      </div>
    );
  }
}
