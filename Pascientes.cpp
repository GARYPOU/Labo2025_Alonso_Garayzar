#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <string>

using namespace std;
struct date{
    int dia;
    int mes;
    int anio;
};

struct pasciente{
    string nombres;
    int edad;
    int histcli;
    date fecha;
};

void cargarpasciente(vector <pasciente> &listado){
  
    pasciente datos;
   
        cout<<"ingrese nombre"<<endl;
        cin>>datos.nombres;
        cout<<"ingrese edad"<<endl;
        cin>>datos.edad;
        cout<<"ingrese numero de historia clinica"<<endl;
        cin>>datos.histcli;
        cout<<"ingrese dia de la consulta"<<endl;
        cin>>datos.fecha.dia;
        cout<<"ingrese mes de la consulta"<<endl;
        cin>>datos.fecha.mes;
        cout<<"ingrese anio de la consulta"<<endl;
        cin>>datos.fecha.anio;
        listado.push_back(datos);
    
       

}


void cancelarpasciente(vector <pasciente> &listado){
    int histcli;
    int pos=0;
    cout<<"ingrese el numero de historia clinica a cancelar"<<endl;
    cin>>histcli;
    for(int i=0;i<listado.size();i++){
        if(listado[i].histcli==histcli){
            pos=i;
      
        }
    }
        listado.erase(listado.begin()pos);
    
}
int citaspordia(vector <pasciente> listado){
    int day;
    int month;
    int year;
    int contador=0;
    cout<<"ingrese el dia de consultas"<<endl;
    cin>>day;
    cout<<"ingrese el mes de consultas"<<endl;
    cin>>month;
    cout<<"ingrese el anio de consultas"<<endl;
    cin>>year;

    for(int i=0;i<listado.size();i++){
        if(listado[i].fecha.dia==day and listado[i].fecha.mes==month and listado[i].fecha.anio==year){
            cout<<listado[i].fecha.dia<<"/"<<listado[i].fecha.mes<<"/"<<listado[i].fecha.anio<<endl;
            contador=contador+1;
        }
    }
    return contador;
}
void muestrapasciente(vector <pasciente> listado){
    int codigo=0;
    cout<<"ingrese el numero de historia clinica del pasciente que quiera buscar"<<endl;
    cin>>codigo;
        for(int i=0;i<listado.size();i++){
            if(listado[i].histcli==codigo){
            cout << listado[i].nombres << " ";
            cout << listado[i].edad << " ";
            cout << listado[i].histcli << " ";
            cout << listado[i].fecha.dia << '/';
            cout << listado[i].fecha.mes << '/';
            cout << listado[i].fecha.anio << endl;   
        
            }
        }
  
}



int main (void)
{
    pasciente datos;
    vector <pasciente> listado;
    int opcion;
    do{
        cout<<"1. Cargar pasciente"<<endl;
        cout<<"2. Cancelar pasciente"<<endl;
        cout<<"3. Citas por dia"<<endl;
        cout<<"4. Mostrar pasciente"<<endl;
        cout<<"5. Salir"<<endl;
        cin>>opcion;
        switch(opcion){
            case 1:
                cargarpasciente(listado);
                break;
            case 2:
                cancelarpasciente(listado);
                break;
            case 3:
                cout<<citaspordia(listado)<<endl;
                break;
            case 4:
                muestrapasciente(listado);
                break;
            case 5:
                break;
        }
    }while(opcion!=5);
}
