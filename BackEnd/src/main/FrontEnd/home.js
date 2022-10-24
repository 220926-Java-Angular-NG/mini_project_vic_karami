let currentUser = localStorage.getItem("currentUser");
currentUser = JSON.parse(currentUser);
console.log(currentUser);
console.log(localStorage);

let welcomeLabel = document.getElementById("welcome-label");
welcomeLabel.innerHTML = `Welcome, ${currentUser.firstName}! You are ${currentUser.horoscopeSign}.`;

async function horoscope(){
    try {
        const raw_response = await fetch(`http://sandipbgt.com/theastrologer/api/horoscope/${currentUser.horoscopeSign}/today`);

        if (!raw_response.ok) {
           // throw new Error(raw_response.status);
            alert(`Error Status: ${raw_response.status}`)
        }

        const json_data = await raw_response.json();

        let input = document.getElementById("input");
        input.innerHTML = json_data.horoscope;


        let br = document.createElement("br");
        input.append(br);
        input.append(br);
        
        currentUser.mood = json_data.meta.mood;
        console.log(currentUser);

    } catch (error) {
        console.log(error);
    }
}
horoscope();

let moodButton = document.getElementById("request-mood");

moodButton.addEventListener("click", async(event)=>{
    let mood = document.getElementById("mood");
    let moodLabel = document.createElement('label');
        moodLabel.innerHTML = `Your mood is ${currentUser.mood}`;
        mood.append(moodLabel);

        let json = JSON.stringify(currentUser);


        try {
    
            const raw_response = await fetch(`http://localhost:8080/mood`,{
                method:"POST", // we add the http to be executed
                body:json
            });
    
            if(!raw_response.ok){
                throw new Error(raw_response.status)
            }
    
            // setTimeout( ()=>{
            //     window.location.replace("home.html")
            // }, 1000)
    
    
        }catch(error){
            console.log(error)
        }


})
