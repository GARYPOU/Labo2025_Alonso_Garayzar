using namespace std;
#include <iostream>
#include <vector>
struct date{
    int dia;
    int mes;
    int año;
};
struct jugador{
  int numero;
  string nombre;
  int goles;
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
        getline(cin,opcion);
        equipo.nombre=opcion;
        for (int i = 0; i < 12; i++)
        {
            cout<<"ingrese el numero del jugador"<<endl;
            cin>>jugador.numero;
            cout<<"ingrese el nombre del jugador"<<endl;
            cin>>jugador.nombre;
            cout<<"ingrese la posicion del jugador"<<endl;
            cin>>jugador.posicion;
            jugador.goles=0;
            equipo.jugadores.push_back(jugador);
            }
        
        }
        
    }
    void ingresarpartido(vector<equipo> &equipos){
        string opcion;
        string nombre;
        partido partido;
        cout<<"desea ingresar un partido, sino ingrese salir"<<endl;
        cin>>opcion;
        while(opcion!="salir"){
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
            cin>>partido.fecha.año;
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
            
        }
        for(int i=0; i<equipos.size();i++){
            equipos[i].partidos.push_back(partido);
        }
      
   
        ordenar(equipos);

}
void mostrarpartrido(vector <equipo> equipos){
    string local;
    string visitante;
   

    cout<<"ingrese nombre del local"<<endl;
    getline(cin,local);
    cout<<"ingrese nombre del visitante"<<endl;
    getline(cin,visitante);
    for(int i=0; i<equipos.size();i++){
        for(int j=0; j<equipos.size();j++){
        {     
            if(equipos[j].nombre==local && equipos[i].nombre==visitante){
                cout<<"nombre del equipo local: "<<equipos[i].partidos[j].nombrelocal<<endl;
                cout<<"nombre del equipo visitante: "<<equipos[i].partidos[j].nombrevisitante<<endl;
                cout<<"goles del equipo local: "<<equipos[i].partidos[j].goleslocal<<endl;
                cout<<"goles del equipo visitante: "<<equipos[i].partidos[j].golesvisitante<<endl;
                cout<<"fecha del partido: "<<equipos[i].partidos[j].fecha.dia<<"/"<<equipos[i].partidos[j].fecha.mes<<"/"<<equipos[i].partidos[j].fecha.año<<endl;
            }
        }
    
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
            cout<<"fecha del partido: "<<equipos[i].partidos[j].fecha.dia<<"/"<<equipos[i].partidos[j].fecha.mes<<"/"<<equipos[i].partidos[j].fecha.año<<endl;

        }

        
    }
}


int main(){
    int opcion=1;
    vector<equipo> equipos=
    {
        {"Real Madrid", {{1, "Thibaut Courtois", "Portero", 0}, {2, "Dani Carvajal", "Defensa", 0}, {3, "Éder Militão", "Defensa", 0}, {4, "David Alaba", "Defensa", 0}, {5, "Ferland Mendy", "Defensa", 0}, {6, "Toni Kroos", "Mediocampista", 0}, {7, "Luka Modric", "Mediocampista", 0}, {8, "Jude Bellingham", "Mediocampista", 0}, {9, "Vinicius Jr.", "Delantero", 0}, {10, "Rodrygo", "Delantero", 0}, {11, "Karim Benzema", "Delantero", 0}}, 10, 5, 1, {{1, "Real Madrid", "Barcelona", 3, 2, {1, 10, 2022}}}},
        {"Barcelona", {{1, "Marc-André ter Stegen", "Portero", 0}, {2, "Jules Koundé", "Defensa", 0}, {3, "Ronald Araújo", "Defensa", 0}, {4, "Andreas Christensen", "Defensa", 0}, {5, "Alejandro Balde", "Defensa", 0}, {6, "Frenkie de Jong", "Mediocampista", 0}, {7, "Pedri", "Mediocampista", 0}, {8, "Gavi", "Mediocampista", 0}, {9, "Robert Lewandowski", "Delantero", 0}, {10, "Raphinha", "Delantero", 0}, {11, "Ousmane Dembélé", "Delantero", 0}}, 8, 6, 2, {{2, "Barcelona", "Atlético de Madrid", 1, 1, {1, 10, 2022}}}},
        {"Atlético de Madrid", {{1, "Jan Oblak", "Portero", 0}, {2, "José María Giménez", "Defensa", 0}, {3, "Stefan Savic", "Defensa", 0}, {4, "Mario Hermoso", "Defensa", 0}, {5, "Nahuel Molina", "Defensa", 0}, {6, "Koke", "Mediocampista", 0}, {7, "Marcos Llorente", "Mediocampista", 0}, {8, "Rodrigo De Paul", "Mediocampista", 0}, {9, "Álvaro Morata", "Delantero", 0}, {10, "Antoine Griezmann", "Delantero", 0}, {11, "Memphis Depay", "Delantero", 0}}, 7, 4, 3, {{3, "Atlético de Madrid", "Real Madrid", 2, 0, {1, 10, 2022}}}},
        {"Sevilla", {{1, "Yassine Bounou", "Portero", 0}, {2, "Jesús Navas", "Defensa", 0}, {3, "Marcos Acuña", "Defensa", 0}, {4, "Tanguy Nianzou", "Defensa", 0}, {5, "Loïc Badé", "Defensa", 0}, {6, "Ivan Rakitić", "Mediocampista", 0}, {7, "Óliver Torres", "Mediocampista", 0}, {8, "Fernando", "Mediocampista", 0}, {9, "Youssef En-Nesyri", "Delantero", 0}, {10, "Erik Lamela", "Delantero", 0}, {11, "Lucas Ocampos", "Delantero", 0}}, 6, 7, 4, {{4, "Sevilla", "Real Betis", 1, 1, {1, 10, 2022}}}},
        {"Real Betis", {{1, "Claudio Bravo", "Portero", 0}, {2, "Germán Pezzella", "Defensa", 0}, {3, "Luiz Felipe", "Defensa", 0}, {4, "Juan Miranda", "Defensa", 0}, {5, "Youssouf Sabaly", "Defensa", 0}, {6, "Guido Rodríguez", "Mediocampista", 0}, {7, "Sergio Canales", "Mediocampista", 0}, {8, "William Carvalho", "Mediocampista", 0}, {9, "Borja Iglesias", "Delantero", 0}, {10, "Ayoze Pérez", "Delantero", 0}, {11, "Juanmi", "Delantero", 0}}, 5, 6, 5, {{5, "Real Betis", "Villarreal", 2, 2, {1, 10, 2022}}}},
        {"Villarreal", {{1, "Pepe Reina", "Portero", 1}, {2, "Raúl Albiol", "Defensa", 0}, {3, "Juan Foyth", "Defensa", 0}, {4, "Alfonso Pedraza", "Defensa", 0}, {5, "Pau Torres", "Defensa", 0}, {6, "Dani Parejo", "Mediocampista", 0}, {7, "Étienne Capoue", "Mediocampista", 0}, {8, "Francis Coquelin", "Mediocampista", 0}, {9, "Gerard Moreno", "Delantero", 0}, {10, "Nicolas Jackson", "Delantero", 0}, {11, "Samuel Chukwueze", "Delantero", 0}}, 7, 5, 6, {{6, "Villarreal", "Real Madrid", 1, 3, {1, 10, 2022}}}},
        {"Athletic Club", {{1, "Unai Simón", "Portero", 0}, {2, "Óscar de Marcos", "Defensa", 0}, {3, "Yeray Álvarez", "Defensa", 0}, {4, "Iñigo Martínez", "Defensa", 0}, {5, "Dani Vivian", "Defensa", 0}, {6, "Mikel Vesga", "Mediocampista", 0}, {7, "Oihan Sancet", "Mediocampista", 0}, {8, "Iker Muniain", "Mediocampista", 0}, {9, "Iñaki Williams", "Delantero", 0}, {10, "Nico Williams", "Delantero", 0}, {11, "Gorka Guruzeta", "Delantero", 0}}, 6, 5, 7, {{7, "Athletic Club", "Real Sociedad", 1, 1, {1, 10, 2022}}}},
        {"Real Sociedad", {{1, "Álex Remiro", "Portero", 0}, {2, "Andoni Gorosabel", "Defensa", 0}, {3, "Robin Le Normand", "Defensa", 0}, {4, "Igor Zubeldia", "Defensa", 0}, {5, "Aihen Muñoz", "Defensa", 0}, {6, "Martín Zubimendi", "Mediocampista", 0}, {7, "Mikel Merino", "Mediocampista", 0}, {8, "David Silva", "Mediocampista", 0}, {9, "Alexander Sørloth", "Delantero", 0}, {10, "Takefusa Kubo", "Delantero", 0}, {11, "Brais Méndez", "Delantero", 0}}, 6, 4, 8, {{8, "Real Sociedad", "Athletic Club", 1, 1, {1, 10, 2022}}}},    
    };
    
    while (opcion)
    {
        cout<<"Ingresar un equipo=1"<<endl;
        cout<<"Ingresar un partido=2"<<endl;
        cout<<"mostrar unpartido=3"<<endl;
        cout<<"mostrar tabla anual=3"<<endl;
        cout<<"Salir=0"<<endl;
        cin>>opcion;
        if(opcion==1){
            ingresarequipo(equipos);
        }
        else if(opcion==2){
            ingresarpartido(equipos);
        }
        else if(opcion==3){
            mostrarpartrido(equipos);
        }
        else if(opcion==4){
            mostrartabla(equipos);
        }
        else if(opcion==0){
            opcion=false;
        }
    
        }
    }
