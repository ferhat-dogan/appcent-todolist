import React, { Component } from 'react';
import SkyLight from 'react-skylight';

class AddTodoList extends Component {
    constructor(props) {
        super(props);
        this.state = { listName: '' };
    }

    handleChange = (event) => {
        this.setState(
            { [event.target.name]: event.target.value }
        );
    }

    handleSubmit = (event) => {
        event.preventDefault();
        var newList = {
            name: this.state.listName
        };
        this.props.addList(newList);
        this.refs.addDialog.hide();
    }

    cancelSubmit = (event) => {
        event.preventDefault();
        this.refs.addDialog.hide();
    }

    render() {
        return (
            <div>
                <SkyLight hideOnOverlayClicked ref="addDialog">
                    <h3>New List</h3>
                    <form>
                        <input type="text" placeholder="Name of the list" name="listName" onChange={this.handleChange} /><br />
                        <button onClick={this.handleSubmit}>Save</button>
                        <button onClick={this.cancelSubmit}>Cancel</button>
                    </form>
                </SkyLight>
                <div>
                    <button style={{ 'margin': '10px' }} onClick={() => this.refs.addDialog.show()}>New List</button>
                </div>
            </div>
        );
    }
}

export default AddTodoList;