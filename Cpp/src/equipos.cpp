using namespace std;
#include <iostream>
#include <vector>
struct date{
    int dia;
    int mes;
    int anio;
};
struct jugador{
  int numero;
  string nombre;
  string posicion;
 
   
};
struct partido{
    int numero;
    string nombrelocal;
    string nombrevisitante;
    int goleslocal;
    int golesvisitante;
    date fecha;
    
};
struct equipo{
    string nombre;
    vector <jugador> jugadores;
    int golesfav;
    int golescon;
    int puntos;
    int pos;
    vector <partido> partidos;
};


void ordenar(vector <equipo> &equipos){
    
    equipo aux = equipos[0];
   
    for(int i=0; i<equipos.size();i++){
        for(int j=0; j<equipos.size()-i-1;j++){
            if(equipos[j].puntos<equipos[j+1].puntos){
                aux=equipos[j];
                equipos[j]=equipos[j+1];
                equipos[j+1]=aux;
                if(equipos[j].puntos==equipos[j+1].puntos){
                    if(equipos[j].golesfav-equipos[j].golescon<equipos[j+1].golesfav-equipos[j+1].golescon){
                        
                        aux=equipos[j];
                        equipos[j]=equipos[j+1];
                        equipos[j+1]=aux;
                    }
           
                
            
            }
                
            }
    for (int i = 0; i < equipos.size(); i++)
    {
        equipos[i].pos=i+1;
    }
    }
        
}
}


void ingresarequipo(vector<equipo> &equipos){
    string opcion;
    string nombre;
    jugador jugador;
    equipo equipo;
    cout<<"desea ingresar un equipo, sino ingrese salir"<<endl;
    cin>>opcion;
    while(opcion!="salir"){
        cout<<"ingrese el nombre del equipo, cuando desee salir, ingrese salir"<<endl;
        cin>>opcion;
        equipo.nombre=opcion;
        if(opcion!="salir"){
        for (int i = 0; i < 1; i++)
        {
            cout<<"ingrese el numero del jugador"<<endl;
            cin>>jugador.numero;
            cout<<"ingrese el nombre del jugador"<<endl;
            cin>>jugador.nombre;
            cout<<"ingrese la posicion del jugador"<<endl;
            cin>>jugador.posicion;
            
            equipo.jugadores.push_back(jugador);
            }
        
        }
        
    }
}
    void ingresarpartido(vector<equipo> &equipos){
        string opcion;
        string nombre;
        partido partido;
            cout<<"desea ingresar un partido, sino ingrese salir"<<endl;
            cin>>opcion;
            cout<<"ingrese numero de partido"<<endl;
            cin>>partido.numero;
            cout<<"ingrese el nombre del equipo local"<<endl;
            cin>>partido.nombrelocal;
            cout<<"ingrese el nombre del equipo visitante"<<endl;
            cin>>partido.nombrevisitante;
            cout<<"ingrese los goles del equipo local"<<endl;
            cin>>partido.goleslocal;
            cout<<"ingrese los goles del equipo visitante"<<endl;
            cin>>partido.golesvisitante;
            cout<<"ingrese el dia del partido"<<endl;
            cin>>partido.fecha.dia;
            cout<<"ingrese el mes del partido"<<endl;
            cin>>partido.fecha.mes;
            cout<<"ingrese el año del partido"<<endl;
            cin>>partido.fecha.anio;
            for (int i = 0; i < equipos.size(); i++)
            {
                if(equipos[i].nombre==partido.nombrelocal){
                    equipos[i].golesfav+=partido.goleslocal;
                    equipos[i].golescon+=partido.golesvisitante;
                    if(partido.goleslocal>partido.golesvisitante){
                        equipos[i].puntos+=3;
                    }
                    else if(partido.goleslocal==partido.golesvisitante){
                        equipos[i].puntos+=1;
                    
                    }
                    else{
                        equipos[i].puntos+=0;
                    }
                    
                }
                if(equipos[i].nombre==partido.nombrevisitante){
                    equipos[i].golesfav+=partido.golesvisitante;
                    equipos[i].golescon+=partido.goleslocal;
                    if(partido.golesvisitante>partido.goleslocal){
                        equipos[i].puntos+=3;
                    }
                    else if(partido.golesvisitante==partido.goleslocal){
                        equipos[i].puntos+=1;
                    
                    }
                    else{
                        equipos[i].puntos+=0;
                    }
                    
                }
            }
            
        
        for(int i=0; i<equipos.size();i++){
            if(equipos[i].nombre==partido.nombrelocal){
            equipos[i].partidos.push_back(partido);
            }
        }
      
   
        ordenar(equipos);

}
void mostrarpartrido(vector <partido> partidos){
    string local;
    string visitante;
   

    cout<<"ingrese nombre del local"<<endl;
    cin>>local;
    cout<<"ingrese nombre del visitante"<<endl;
    cin>>visitante;
    for(int i=0; i<partidos.size();i++){
            if(partidos[i].nombrelocal==local && partidos[i].nombrevisitante==visitante){
                cout<<"nombre del equipo local: "<<partidos[i].nombrelocal<<endl;
                cout<<"nombre del equipo visitante: "<<partidos[i].nombrevisitante<<endl;
                cout<<"goles del equipo local: "<<partidos[i].goleslocal<<endl;
                cout<<"goles del equipo visitante: "<<partidos[i].golesvisitante<<endl;
                cout<<"fecha del partido: "<<partidos[i].fecha.dia<<"/"<<partidos[i].fecha.mes<<"/"<<partidos[i].fecha.anio<<endl;
            }
        }
    
}



void mostrartabla(vector <equipo> &equipos){
    for(int i=0; i<equipos.size();i++){
        cout<<"posicion: "<<equipos[i].pos<<endl;
        cout<<"nombre del equipo: "<<equipos[i].nombre<<endl;
        cout<<"goles a favor: "<<equipos[i].golesfav<<endl;
        cout<<"goles en contra: "<<equipos[i].golescon<<endl;
        cout<<"puntos: "<<equipos[i].puntos<<endl;
        for(int j=0; j<equipos[i].partidos.size();j++){
            cout<<"partido numero: "<<equipos[i].partidos[j].numero<<endl;
            cout<<"nombre del equipo local: "<<equipos[i].partidos[j].nombrelocal<<endl;
            cout<<"nombre del equipo visitante: "<<equipos[i].partidos[j].nombrevisitante<<endl;
            cout<<"goles del equipo local: "<<equipos[i].partidos[j].goleslocal<<endl;
            cout<<"goles del equipo visitante: "<<equipos[i].partidos[j].golesvisitante<<endl;
            cout<<"fecha del partido: "<<equipos[i].partidos[j].fecha.dia<<"/"<<equipos[i].partidos[j].fecha.mes<<"/"<<equipos[i].partidos[j].fecha.anio<<endl;

        }

        
    }
}


int main(){
    int opcion=1;
    vector<partido>partidos;
    vector<equipo> equipos=
    {
        {"RealMadrid", {{1, "Thibaut Courtois", "Portero"}, {2, "Dani Carvajal", "Defensa"}, {3, "Éder Militão", "Defensa"}, {4, "David Alaba", "Defensa"}, {5, "Ferland Mendy", "Defensa"}, {6, "Toni Kroos", "Mediocampista"}, {7, "Luka Modric", "Mediocampista"}, {8, "Jude Bellingham", "Mediocampista"}, {9, "Vinicius Jr.", "Delantero"}, {10, "Rodrygo", "Delantero"}, {11, "Karim Benzema", "Delantero"}}, 0, 0, 0, 1, {{1, "RealMadrid", "Barcelona", 0, 0, {1, 10, 2022}}}},
        {"Barcelona", {{1, "Marc-André ter Stegen", "Portero"}, {2, "Jules Koundé", "Defensa"}, {3, "Ronald Araújo", "Defensa"}, {4, "Andreas Christensen", "Defensa"}, {5, "Alejandro Balde", "Defensa"}, {6, "Frenkie de Jong", "Mediocampista"}, {7, "Pedri", "Mediocampista"}, {8, "Gavi", "Mediocampista"}, {9, "Robert Lewandowski", "Delantero"}, {10, "Raphinha", "Delantero"}, {11, "Ousmane Dembélé", "Delantero"}}, 0, 0, 0, 2, {{1, "RealMadrid", "Barcelona", 0, 0, {1, 10, 2022}}}},
        {"Atlético de Madrid", {{1, "Jan Oblak", "Portero"}, {2, "José María Giménez", "Defensa"}, {3, "Stefan Savic", "Defensa"}, {4, "Mario Hermoso", "Defensa"}, {5, "Nahuel Molina", "Defensa"}, {6, "Koke", "Mediocampista"}, {7, "Marcos Llorente", "Mediocampista"}, {8, "Rodrigo De Paul", "Mediocampista"}, {9, "Álvaro Morata", "Delantero"}, {10, "Antoine Griezmann", "Delantero"}, {11, "Memphis Depay", "Delantero"}}, 0, 0, 0, 3, {{1, "AtléticoDeMadrid", "RealBetis", 0, 0, {1, 10, 2022}}}},
        {"Sevilla", {{1, "Yassine Bounou", "Portero"}, {2, "Jesús Navas", "Defensa"}, {3, "Marcos Acuña", "Defensa"}, {4, "Tanguy Nianzou", "Defensa"}, {5, "Loïc Badé", "Defensa"}, {6, "Ivan Rakitić", "Mediocampista"}, {7, "Óliver Torres", "Mediocampista"}, {8, "Fernando", "Mediocampista"}, {9, "Youssef En-Nesyri", "Delantero"}, {10, "Erik Lamela", "Delantero"}, {11, "Lucas Ocampos", "Delantero"}}, 0, 0, 0, 4, {{1, "AthleticClub", "Sevilla", 0, 0, {1, 10, 2022}}}},
        {"RealBetis", {{1, "Claudio Bravo", "Portero"}, {2, "Germán Pezzella", "Defensa"}, {3, "Luiz Felipe", "Defensa"}, {4, "Juan Miranda", "Defensa"}, {5, "Youssouf Sabaly", "Defensa"}, {6, "Guido Rodríguez", "Mediocampista"}, {7, "Sergio Canales", "Mediocampista"}, {8, "William Carvalho", "Mediocampista"}, {9, "Borja Iglesias", "Delantero"}, {10, "Ayoze Pérez", "Delantero"}, {11, "Juanmi", "Delantero"}}, 0, 0, 0, 5, {{1, "AtléticoDeMadrid", "RealBetis", 0, 0, {1, 10, 2022}}}},
        {"Villarreal", {{1, "Pepe Reina", "Portero"}, {2, "Raúl Albiol", "Defensa"}, {3, "Juan Foyth", "Defensa"}, {4, "Alfonso Pedraza", "Defensa"}, {5, "Pau Torres", "Defensa"}, {6, "Dani Parejo", "Mediocampista"}, {7, "Étienne Capoue", "Mediocampista"}, {8, "Francis Coquelin", "Mediocampista"}, {9, "Gerard Moreno", "Delantero"}, {10, "Nicolas Jackson", "Delantero"}, {11, "Samuel Chukwueze", "Delantero"}}, 0, 0, 0, 6, {{1, "RealSociedad", "Villarreal", 0, 0, {1, 10, 2022}}}},
        {"Athletic Club", {{1, "Unai Simón", "Portero"}, {2, "Óscar de Marcos", "Defensa"}, {3, "Yeray Álvarez", "Defensa"}, {4, "Iñigo Martínez", "Defensa"}, {5, "Dani Vivian", "Defensa"}, {6, "Mikel Vesga", "Mediocampista"}, {7, "Oihan Sancet", "Mediocampista"}, {8, "Iker Muniain", "Mediocampista"}, {9, "Iñaki Williams", "Delantero"}, {10, "Nico Williams", "Delantero"}, {11, "Gorka Guruzeta", "Delantero"}}, 0, 0, 0, 7, {{1, "AthleticClub", "Sevilla", 0, 0, {1, 10, 2022}}}},
        {"RealSociedad", {{1, "Álex Remiro", "Portero"}, {2, "Andoni Gorosabel", "Defensa"}, {3, "Robin Le Normand", "Defensa"}, {4, "Igor Zubeldia", "Defensa"}, {5, "Aihen Muñoz", "Defensa"}, {6, "Martín Zubimendi", "Mediocampista"}, {7, "Mikel Merino", "Mediocampista"}, {8, "David Silva", "Mediocampista"}, {9, "Alexander Sørloth", "Delantero"}, {10, "Takefusa Kubo", "Delantero"}, {11, "Brais Méndez", "Delantero"}}, 0, 0, 0, 8, {{1, "RealSociedad", "Villareal", 0, 0, {1, 10, 2022}}}},    
    };
    
    while (opcion)
    {   
        cout<<"_____________________________________________________________________"<<endl;
        cout<<"Ingresar un equipo=1"<<endl;
        cout<<"Ingresar un partido=2"<<endl;
        cout<<"mostrar un partido=3"<<endl;
        cout<<"mostrar tabla anual=4"<<endl;
        cout<<"Salir=0"<<endl;
        cout<<"_____________________________________________________________________"<<endl;
        cin>>opcion;
        if(opcion==1){
            ingresarequipo(equipos);
        }
        else if(opcion==2){
            ingresarpartido(equipos);
        }
        else if(opcion==3){
            mostrarpartrido(partidos);
        }
        else if(opcion==4){
            mostrartabla(equipos);
        }
        else if(opcion==0){
            opcion=false;
        }
    
        }
    }
