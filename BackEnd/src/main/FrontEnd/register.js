let backButton = document.getElementById("back-button");

backButton.addEventListener('click', (event) => {
    event.preventDefault();
    window.location.replace("index.html")
});

let registerButton = document.getElementById('register-button');

registerButton.addEventListener('click', async (event) =>{
    let firstName1 = document.getElementById('first-name').value;
    let lastName1 = document.getElementById('last-name').value;
    let email1 = document.getElementById('email').value;
    let password1 = document.getElementById('password').value;
    let password11 = document.getElementById('password1').value;
    let sign1 = document.getElementById('sign').value;
    let mood1 = "";
    if (password1 == password11){

        console.log(firstName1);
    console.log(lastName1);
    console.log(email1);
    console.log(password1);
    console.log(password11);
    console.log(sign1);
    console.log(mood1);
        

        // Creating an JSON Object using the input from the user
        let user = {
            firstName: firstName1,
            lastName: lastName1,
            email:email1,
            password:password1,
            horoscopeSign:sign1,
            mood:mood1
        }

        let json = JSON.stringify(user);
        console.log(json);

        try {

            const raw_response = await fetch(`http://localhost:8080/register`,{
            method:"POST", // we add the http to be executed
            body:json
            });
    
            if(!raw_response.ok){
                throw new Error(raw_response.status)
            }
    
            raw_response.json().then( (data) => {
    
                let loggedInUser = JSON.stringify(data)

                localStorage.setItem("currentUser",loggedInUser)
                console.log(loggedInUser);
            });

    
            setTimeout( ()=>{
                window.location.replace("home.html")
            }, 1000 )
    
    
        }catch(error){
            console.log(error)
        }
    } else {
        alert("Passwords do NOT match");
    }   
});

