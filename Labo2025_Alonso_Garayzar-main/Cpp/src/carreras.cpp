#include <iostream>
#include <vector>
using namespace std;
int num=0;
struct Vuelta_rapida {
    int numero_vuelta;
    int tiempo;
    string nombre_piloto;
  };
      
  struct Equipo {
     string nombre;
     int puntos;
  };

struct piloto {
    int numerocarrera;
    string nombrepil;
    string autito;
    string equipo;
    int posarranque;
    int posfinal;
    int puntos;
    bool vueltarapida;
};
void cargarequipo(vector <Equipo> &equipos){
    Equipo equi;
    int cont=0;
while(cont<2){
    cout<<"ingrese nombre del equipo"<<endl;
    cin>>equi.nombre;
    equi.puntos=0;
    equipos.push_back(equi);
    cont=cont+1;
    }
}

void cargar_datos_carrera(vector <piloto> &pilotos, vector <Vuelta_rapida> &Vueltas_rapidas){
    piloto pil;
    Vuelta_rapida vueltarapida;
    string vuelta;
    int pilo=0;
    num=num+1;
    while(pilo<4){
        pil.numerocarrera=num;
        cout<<"-------------------------------------------------------------------------------------------------------------------"<<endl;
        cout<<"ingrese nombre del piloto"<<endl;
        cin>>pil.nombrepil;
        cout<<"ingrese nombre del auto"<<endl;
        cin>>pil.autito;
        cout<<"ingrese nombre del equipo"<<endl;
        cin>>pil.equipo;
        cout<<"ingrese posicion de arranque"<<endl;
        cin>>pil.posarranque;
        cout<<"ingrese posicion final"<<endl;
        cin>>pil.posfinal;
        cout<<"hizo la vuelta mas rapida? si/no"<<endl; 
        cin>>vuelta;
            if(vuelta=="si"){
            pil.vueltarapida=true;
            cout<<"ingrese el numero de vuelta"<<endl;
            cin>>vueltarapida.numero_vuelta;
            cout<<"ingrese el tiempo de la vuelta"<<endl;
            cin>>vueltarapida.tiempo;
            vueltarapida.nombre_piloto=pil.nombrepil;
        }
        pilo=pilo+1;
        pilotos.push_back(pil);
        
    }
    Vueltas_rapidas.push_back(vueltarapida);
}   


void calcular_puntaje_piloto_equipo(vector<Equipo> &equipos, vector<piloto> &pilotos, vector<Vuelta_rapida> vueltas_rapidas){
    for(int i=0; i<pilotos.size();i++){
        if(pilotos[i].posfinal==1){
            pilotos[i].puntos=25;
        }
        else if(pilotos[i].posfinal==2){
            pilotos[i].puntos=18;
        }
        else if(pilotos[i].posfinal==3){
            pilotos[i].puntos=15;
        }
        else if(pilotos[i].posfinal==4){
            pilotos[i].puntos=12;
        }
        else if(pilotos[i].posfinal==5){
            pilotos[i].puntos=10;
        }
        else if(pilotos[i].posfinal==6){
            pilotos[i].puntos=8;
        }
        else if(pilotos[i].posfinal==7){
            pilotos[i].puntos=6;
        }
        else if(pilotos[i].posfinal==8){
            pilotos[i].puntos=4;
        }
        else if(pilotos[i].posfinal==9){
            pilotos[i].puntos=2;
        }
        else if(pilotos[i].posfinal==10){
            pilotos[i].puntos=1;
        }
        else{
            pilotos[i].puntos=0;
        }
        if(pilotos[i].posfinal<=10){
            if(pilotos[i].vueltarapida==true){
                for(int j=0; j<vueltas_rapidas.size();j++){
                    if(pilotos[i].nombrepil==vueltas_rapidas[j].nombre_piloto){
                        pilotos[i].puntos=pilotos[i].puntos+1;
                }
            }
        
        }
    }
    for(int j=0; j<equipos.size();j++){
        if(pilotos[i].equipo==equipos[j].nombre){
            equipos[j].puntos=equipos[j].puntos+pilotos[i].puntos;
        }
    }
}
}
void ordenar_tabla_equipos(vector <Equipo> &equipos){
    Equipo aux = equipos[0];
    for(int i=0; i<equipos.size();i++){
       for(int j=0; j<equipos.size()-i-1;j++){
            if(equipos[j].puntos<equipos[j+1].puntos){
                aux=equipos[j];
                equipos[j]=equipos[j+1];
                equipos[j+1]=aux;
           }
       }
   }

}

void mostrar_tabla_equipos(vector <Equipo> &equipos){
    for(int i=1; i<=equipos.size();i++){
        cout<<"posicion: "<<i<<endl;
        cout<<"nombre del equipo: "<<equipos[i-1].nombre<<endl;
        cout<<"puntos: "<<equipos[i-1].puntos<<endl;
    }
}
    

int main(){
    vector<Equipo> equipos;
    vector<piloto> pilotos;
    vector<Vuelta_rapida> Vueltas_rapidas;
    cargarequipo(equipos);
    for (int i = 0; i < 1; i++){
       // 1
       cargar_datos_carrera(pilotos, Vueltas_rapidas);
       // 2
       calcular_puntaje_piloto_equipo(equipos, pilotos, Vueltas_rapidas);
    }
    // 3
    ordenar_tabla_equipos(equipos);
    mostrar_tabla_equipos(equipos);
 }