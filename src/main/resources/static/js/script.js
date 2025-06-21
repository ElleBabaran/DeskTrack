document.addEventListener('DOMContentLoaded', function() {
    // --- Logic for login.html ---
    const loginForm = document.getElementById('loginForm');
    const errorMessage = document.getElementById('error-message');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');

    if (loginForm) {
        loginForm.addEventListener('submit', function(event) {
            event.preventDefault(); 

            const username = usernameInput.value;
            const password = passwordInput.value;

            console.log('Attempting login with:', { username, password });

            if (username === '' || password === '') {
                 errorMessage.textContent = 'Please enter username and password.';
                 errorMessage.style.display = 'block'; 
            } else {

                const loginSuccessful = false; 

                if (loginSuccessful) {
                    errorMessage.style.display = 'none'; 
                    alert('Login successful (simulated)!');

                } else {
                    errorMessage.textContent = 'Incorrect Username or Password';
                    errorMessage.style.display = 'block'; 
                }
            }
        });
    }

    const studentBtn = document.getElementById('studentBtn');
    const facultyBtn = document.getElementById('facultyBtn');

    if (studentBtn) {
        studentBtn.addEventListener('click', function(event) {
            event.preventDefault(); 
            console.log('Student button clicked');

            alert('Student login/selection logic goes here.');
        });
    }

    if (facultyBtn) {

         facultyBtn.addEventListener('click', function(event) {
             console.log('Faculty button clicked');
            
         });
    }


});
