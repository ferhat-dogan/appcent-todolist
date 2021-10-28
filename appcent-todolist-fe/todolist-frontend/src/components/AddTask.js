import React, { Component } from 'react';
import SkyLight from 'react-skylight';

class AddTask extends Component {
    constructor(props) {
        super(props);
        this.state = { taskName: '', taskState: 'uncompleted' };
    }

    handleChange = (event) => {
        this.setState(
            { [event.target.name]: event.target.value }
        );
    }

    handleSubmit = (event) => {
        event.preventDefault();
        var newTask = {
            text: this.state.taskName,
            state: this.state.taskState
        };
        this.props.addTask(newTask);
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
                    <h3>New Task</h3>
                    <form>
                        <input type="text" placeholder="Name of the task" name="taskName" onChange={this.handleChange} /><br />
                        <button onClick={this.handleSubmit}>Save</button>
                        <button onClick={this.cancelSubmit}>Cancel</button>
                    </form>
                </SkyLight>
                <div>
                    <button style={{ 'margin': '10px' }} onClick={() => this.refs.addDialog.show()}>New Task</button>
                </div>
            </div>
        );
    }
}

export default AddTask;