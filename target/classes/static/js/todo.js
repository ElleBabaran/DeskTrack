document.addEventListener('DOMContentLoaded', () => {
    const taskInput = document.getElementById('taskInput');
    const addTaskBtn = document.getElementById('addTaskBtn');
    const todoListDiv = document.getElementById('todoList');

    let tasks = []; 

    
    function renderTasks() {
        todoListDiv.innerHTML = ''; 

        if (tasks.length === 0) {
            const noTasksMessage = document.createElement('p');
            noTasksMessage.style.textAlign = 'center';
            noTasksMessage.style.color = '#777';
            noTasksMessage.textContent = 'No tasks yet. Add one above!';
            todoListDiv.appendChild(noTasksMessage);
            return;
        }

        const ul = document.createElement('ul');
        tasks.forEach((task, index) => {
            const li = document.createElement('li');
            li.setAttribute('data-index', index);

            const taskTextSpan = document.createElement('span');
            taskTextSpan.className = 'task-text';
            taskTextSpan.textContent = task;

            const editInput = document.createElement('input');
            editInput.type = 'text';
            editInput.className = 'edit-input';
            editInput.value = task;

            const actionsDiv = document.createElement('div');
            actionsDiv.className = 'actions';

            const editBtn = document.createElement('button');
            editBtn.className = 'edit';
            editBtn.textContent = 'Edit';
            editBtn.addEventListener('click', () => editTask(index, li, taskTextSpan, editInput, editBtn));

            const saveBtn = document.createElement('button');
            saveBtn.className = 'save';
            saveBtn.textContent = 'Save';
            saveBtn.addEventListener('click', () => saveTask(index, li, taskTextSpan, editInput, editBtn, saveBtn));

            const deleteBtn = document.createElement('button');
            deleteBtn.className = 'delete';
            deleteBtn.textContent = 'Delete';
            deleteBtn.addEventListener('click', () => deleteTask(index));

            actionsDiv.appendChild(editBtn);
            actionsDiv.appendChild(saveBtn);
            actionsDiv.appendChild(deleteBtn);

            li.appendChild(taskTextSpan);
            li.appendChild(editInput);
            li.appendChild(actionsDiv);
            ul.appendChild(li);
        });
        todoListDiv.appendChild(ul);
    }

    function addTask() {
        const newTask = taskInput.value.trim();
        if (newTask !== '') {
            tasks.push(newTask);
            taskInput.value = '';
            renderTasks();
        } else {
            alert('Please enter a task!');
        }
    }

    function editTask(index, listItem, taskTextSpan, editInput, editBtn) {
        listItem.classList.add('editing');
        editInput.focus(); 
    }

    function saveTask(index, listItem, taskTextSpan, editInput, editBtn, saveBtn) {
        const updatedTask = editInput.value.trim();
        if (updatedTask !== '') {
            tasks[index] = updatedTask;
            listItem.classList.remove('editing');
            renderTasks(); 
        } else {
            alert('Task cannot be empty!');
        }
    }

    function deleteTask(index) {
        if (confirm('Are you sure you want to delete this task?')) {
            tasks.splice(index, 1); 
            renderTasks(); 
        }
    }

    addTaskBtn.addEventListener('click', addTask);
    taskInput.addEventListener('keypress', (event) => {
        if (event.key === 'Enter') {
            addTask();
        }
    });

    renderTasks();
});