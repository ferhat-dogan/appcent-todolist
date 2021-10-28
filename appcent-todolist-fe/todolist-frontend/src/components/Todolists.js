import React, { Component } from 'react';
import { SERVER_URL } from '../constants.js';
import ReactTable from "react-table";
import 'react-table/react-table.css';
import { Route } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';
import AddTodoList from './AddTodoList.js';

class Todolists extends Component {
    constructor(props) {
        super(props);
        this.state = { lists: [] }
    }

    componentDidMount() {
        this.fetchLists();
    }

    fetchLists = () => {
        fetch(SERVER_URL + 'api/todoLists')
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    lists: responseData._embedded.todoLists
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
                toast.success("To-do list deleted", {
                    position: toast.POSITION.BOTTOM_LEFT
                });
                this.fetchLists();
            })
            .catch(err => {
                toast.error("Error when deleting", {
                    position: toast.POSITION.BOTTOM_LEFT
                });
                console.error(err)
            })
    }

    addList(list) {
        fetch(SERVER_URL + 'api/todoLists',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(list)
            })
            .then(res => this.fetchLists())
            .catch(err => console.error(err))
    }

    render() {
        const columns = [{
            Header: 'List Name',
            accessor: 'name'
        },
        {
            id: 'gobutton',
            sortable: false,
            filterable: false,
            width: 100,
            accessor: '_links.tasks.href',
            Cell: ({ value }) => (<Route render={({ history }) => (<button onClick={() => { history.push('/tasksOfList'); this.props.parentCallback(value); }}>Show</button>)} />)
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
                <AddTodoList addList={this.addList} fetchLists={this.fetchLists} />
                <ReactTable data={this.state.lists} columns={columns} filterable={true} pageSize={10} />
                <ToastContainer autoClose={1500} />
            </div>
        );
    }
}

export default Todolists;