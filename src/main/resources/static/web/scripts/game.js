let info;
let url = new URLSearchParams(location.search);
let gp= url.get('gp');

function getData(gpId){
    
        document.getElementById('dock').innerHTML = `<div id="display">
                                                    
                                                    </div>    
                                                    <div id="controlTiros">
                                                        </div>`;
        document.getElementById("datosPartida").innerHTML = "";
        
        document.getElementById('grid').innerHTML = "";
        //document.getElementById('grid-salvo').innerHTML = "";
    
        createGrid(11, document.getElementById('grid'), 'ships')

        fetch(`/api/game_view/${gpId}`,{
              method: 'GET'
          }).then(function(respt){
        if(respt.ok)
            return respt.json();
        else
            throw new Error;
        }).then(function(json){
            info = json;
            gp= gp;
            mostrarDatos();
            mostrarShip();
            mostrarGrillaSalvo();
            mostrarSalvo();
            
            
        }).catch(function(error){
            console.log(error);
        })
}
getData(gp);
addSalvoesMe();
function mostrarDatos (){
    let datos = document.getElementById("datosPartida");
    let aux = `<b>GAME: ${info.idGame}</b>
               <p>${info.startCreator}</p>`;
    for(let i = 0 ; i < info.gamePlayers.length ; i++){
        if(info.gamePlayers[i].player.userName != info.startCreator)
            aux += `VS 
                    <br>
                    <p>${info.gamePlayers[i].player.userName}(viewer)</p>`;
    }
    datos.innerHTML += aux;
}
 
 function isHorizontal(list){
            return list[0][0] == list[1][0]? 'horizontal': 'vertical';
}
function mostrarShip(){
        let ships= info.ships;
        if(ships.length >0 ){
            for(let x=0; x<ships.length; x++ ){
                createShips(ships[x].type, ships[x].locations.length,isHorizontal(ships[x].locations),document.getElementById('ships'+ships[x].locations[0]),true)
            }
        }else{
            document.getElementById('display').innerHTML += `<p> Welcome...</p>`
            document.getElementById("controlTiros").innerHTML += '<div><button type="button" id="btn-AddShip" onclick="getShipsLocations()" disabled>Add Ships</button></div>'
            createShips('carrier', 5, 'horizontal', document.getElementById('dock'),false)
            createShips('battleship', 4, 'horizontal', document.getElementById('dock'),false)
            createShips('submarine', 3, 'horizontal', document.getElementById('dock'),false)
            createShips('destroyer', 3, 'horizontal', document.getElementById('dock'),false)
            createShips('patrol_boat', 2, 'horizontal', document.getElementById('dock'),false)
        }
            
}
function mostrarGrillaSalvo(){
    if(info.gamePlayers.length > 1){
        if(info.ships.length>0){//tengo los ships en la grilla ya puestos
            //createGrid(11, document.getElementById('grid-salvo'), 'salvo')
            
            //addSalvoesMe();
            document.getElementById('grid-salvo').style.display = "block";
            document.getElementById('controlTiros').innerHTML += `<div><button type="button" id="btn-Fire" onclick="getSalvoes()">Fire!</button></div>`
        }else
            document.getElementById('grid-salvo').style.display = "none";
    }else{
        document.getElementById('display').innerHTML = `<p>Waiting opponent..</p>`;
        document.getElementById('grid-salvo').style.display = "none";
    }
        
}
function mostrarSalvo(){
        let salvoes= info.salvo;//tengo todos los tiros de ambos jugadores(si es que los hay)
        let sunken= [];
        
        if(salvoes.length>0){
            info.salvo.forEach(shoot => info.player == shoot.player?
                           (shoot.sunken.length > 0 ? (shoot.sunken.forEach(sunk => sunk.locations.forEach(x => !sunken.includes(x)?sunken.push(x):null))):null):null)
            salvoes.map(user => user.player == info.player ? salvos(user, sunken,'salvoBD', 'salvo') :salvos(user, sunken,'salvoOther', 'ships'));
    
        }
    
}
function salvos(obj,sunks, style, grid){
            let hits= [];
    
            for(let x = 0; x< obj.location.length; x++){
                var cell = document.getElementById(grid+obj.location[x]);
                if(style == 'salvoBD'){
                    
                    cell.classList.remove('shootEnabled');
                    
                    if(obj.hits.includes(obj.location[x])){
                        if(sunks.includes(obj.location[x])){
                            cell.classList.add('salvoBD_sunken') 
                        }else
                            cell.classList.add('salvoBD_hits') 
                    }else
                        cell.classList.add(style)
                           
                    cell.innerHTML = obj.turn;
                }
                else{
                    if( cell.classList.length == 1)
                        cell.classList.add(style+'None');
                    else
                        cell.classList.add(style+'Ok');
                cell.innerHTML+=`<p>${obj.turn}</p>`;
                }
                
            }            
}
/*function checkHitsOrSunked(){
    let hits
}*/
function getShipsLocations(){
    let ships = [];
    document.querySelectorAll(".grid-item").forEach(cell => {
        let ship ={"type":cell.id
        };
        
        ship.locations = [];
        
        if(cell.dataset.orientation == "horizontal"){
           for(i = 0; i < cell.dataset.length; i++){
				ship.locations.push(cell.dataset.y + (parseInt(cell.dataset.x) + i))
           } 
        }else{
            for(i = 0; i < cell.dataset.length; i++){
				ship.locations.push(String.fromCharCode(cell.dataset.y.charCodeAt() + i) + cell.dataset.x)
			}
        }
        
        ships.push(ship);
        
    });
    sendShips(gp,ships);
}
function sendShips (gamePlayerId, ships){
   
    let url = '/api/games/players/'+gamePlayerId+'/ships';
    let init = {
		method: 'POST',
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(ships)
	}
    fetch(url,init)
	.then(res => {
		if(res.ok){
           return res.json()
		}else{
			return Promise.reject(res.json())
		}
	})
    .then(json => {
        getData(gp)
        
	})
	.catch(function(error){
        console.log(error);
    })
    .then(function(json){
        alert(json["error"])
    })
	
}

function addSalvoesMe(){
    for(let i = 0; i < 10; i++){
        for( let j = 0; j < 10; j++){
            document.getElementById('salvo'+String.fromCharCode(i + 65)+(j+1)).addEventListener('click',function(event) {addClassAtSalvo(event)});
        }
    }
}// agrega para que pueda recibir evento 'click' a la grilla salvo

function addClassAtSalvo(ev){
    let id = ev.target.id;
    let cell = ev.target;
    if(cell.classList.contains('shootEnabled')){
        if(document.querySelectorAll('.salvoMe').length < 5 ||document.getElementById(id).classList.contains('salvoMe'))
            document.getElementById(id).classList.toggle('salvoMe')
    }
    ;
} //evento que le agrego a addSalvoesMe

function getSalvoes(){
    let salvoes = [];
    document.querySelectorAll('.salvoMe').forEach(cell => {
        salvoes.push(String.fromCharCode(cell.dataset.y.charCodeAt())+cell.dataset.x)
    })
    sendSalvoes(gp,salvoes);
}   

function sendSalvoes (gamePlayerId, salvoes){
    let url = '/api/games/players/'+gamePlayerId+'/salvoes';
    let init = {
		method: 'POST',
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(salvoes)
	}
    fetch(url,init)
	.then(res => {
		if(res.ok){
           return res.json()
		}else{
			return Promise.reject(res.json())
		}
	})
    .then(json => {
        document.querySelectorAll('.salvoMe').forEach(cell => {
            cell.classList.remove('salvoMe')
        });
        getData(gp)
        
	})
	.catch(function(error){
        console.log(error);
    })

}

