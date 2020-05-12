import React, { Component } from "react";
import { ListGroup, ListGroupItem } from "reactstrap";

export default class CategoryList extends Component {
  constructor(props) {
    super(props);
    this.state = { 
      categories:[
        {categoryId:1, categoryName:'Study'},
        {categoryId:2, categoryName:'Workout'}
      ]
    };
  }

  render() {
    return (
      <div>
        <ListGroup>
        <ListGroupItem><h4>{this.props.info.title}</h4></ListGroupItem>
          {
            this.state.categories.map(category=>(
            <ListGroupItem onClick={() => this.props.changeCategory(category)} key={category.categoryId}>
                {category.categoryName}
            </ListGroupItem>
            ))
          }
        </ListGroup>
      </div>
    );
  }
}
