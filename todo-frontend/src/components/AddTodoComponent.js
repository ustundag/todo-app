import React, { Component } from "react";
import { Container, Row, Col } from "reactstrap";
import { Formik, Form, Field, ErrorMessage } from "formik";
import moment from "moment";
import NaviComponent from "./NaviComponent";
import TodoDataService from '../TodoDataService.js'

export default class AddTodoComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.match.params.id,
      description: null,
      deadline: moment(new Date()).format("YYYY-MM-DD"),
    };
    this.onSubmit = this.onSubmit.bind(this)
  }

  onSubmit(values) {
      console.log("onSubmit clicked")
      let todo = {
          id: this.state.id,
          description: values.description,
          deadline: values.deadline,
          isCompleted: false
      }
      TodoDataService.addTodo(todo).then(() => this.props.history.push('/'));
  }
  validate(values) {
    let errors = {}
    if (!values.description) {
        errors.description = 'Please write a description!'
    }
    return errors
  }
  render() {
    let { description, deadline } = this.state;
    return (
      <div className="AddTodoComponent">
        <Container>
          <Row>
            <Col xs="12">
              <NaviComponent />
            </Col>
          </Row>
          <Row style={{ margin: 20 }}></Row>
          <Row>
            <Col sm="12" md={{ size: 6, offset: 3 }}>
              <div style={{borderBottom: "3px solid rgb(212, 212, 212)", marginBottom: 25}}>
              <h1>Add New Item</h1>
              </div>
              <Formik
                initialValues={{ description, deadline }}
                onSubmit={this.onSubmit}
                validateOnChange={false}
                validateOnBlur={false}
                validate={this.validate}
                enableReinitialize={true}
              >
                {(props) => (
                  <Form>
                    <ErrorMessage name="description" component="div"
                                  className="alert alert-warning" />
                    <fieldset className="form-group">
                      <label>Description</label>
                      <Field
                        className="form-control"
                        type="text"
                        name="description"
                        placeholder="Add description"
                      />
                    </fieldset>
                    <fieldset className="form-group">
                      <label>Deadline</label>
                      <Field
                        className="form-control"
                        type="date"
                        name="deadline"
                      />
                    </fieldset>
                    <button className="btn btn-success" type="submit">
                      Save
                    </button>
                  </Form>
                )}
              </Formik>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
