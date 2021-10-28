import React, { Component } from 'react';
import ReactTable from "react-table";
import 'react-table/react-table.css';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import AddTask from './AddTask';

class Tasks extends Component {
    constructor(props) {
        super(props);
        this.state = { tasks: [] }
    }

    componentDidMount() {
        this.fetchTasks();
    }

    fetchTasks = () => {
        fetch(this.props.tasksLink)
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    tasks: responseData._embedded.tasks
                });
            })
            .catch(err => console.error(err));
    }

    confirmDelete = (link) => {
        confirmAlert({
            message: 'Are you sure to delete?',
            buttons: [
                {
                    label: 'Yes',
                    onClick: () => this.onDelClick(link)
                },
                {
                    label: 'No'
                }
            ]
        })
    }

    onDelClick = (link) => {
        fetch(link, { method: 'DELETE' })
            .then(res => {
                toast.success("Task deleted", {
                    position: toast.POSITION.BOTTOM_LEFT
                });
                this.fetchTasks();
            })
            .catch(err => {
                toast.error("Error when deleting", {
                    position: toast.POSITION.BOTTOM_LEFT
                });
                console.error(err)
            })
    }

    addTask(task) {
        fetch('http://localhost:8080/api/todoLists/1/tasks',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(task)
            })
            .then(res => this.fetchTasks())
            .catch(err => console.error(err))
    }

    render() {
        const columns = [{
            id: 'checkbox',
            sortable: false,
            filterable: false,
            width: 100,
            accessor: 'state',
            Cell: ({ value }) => {
                if (value.localeCompare("uncompleted") === 0) {
                    return <input type="checkbox" />;
                }
                else if (value.localeCompare("completed") === 0) {
                    return <input type="checkbox" checked />;
                }
            }
        }, {
            Header: 'Task Name',
            accessor: 'text'
        },
        {
            id: 'delbutton',
            sortable: false,
            filterable: false,
            width: 100,
            accessor: '_links.self.href',
            Cell: ({ value }) => (<button onClick={() => { this.confirmDelete(value) }}>Delete</button>)
        }]

        return (
            <div className="App">
                <AddTask addTask={this.addTask} fetchTasks={this.fetchTasks} />
                <ReactTable data={this.state.tasks} columns={columns} filterable={true} pageSize={10} />
                <ToastContainer autoClose={1500} />
            </div>
        );
    }
}

export default Tasks;