using namespace std;
#include <iostream>
#include <vector>
struct date{
    int dia;
    int mes;
    int anio;
};
struct empleado_t{
    string nombre;
    string apellido;
    date fecha;
    string sexo;
    int salario;
};
void cargavector(vector<empleado_t> &empleado){
    string nom;
    empleado_t empleados;
    while(nom!="salir"){
    cout<<"Ingresar o Salir"<<endl;
    cin>>nom;
    if(nom=="ingresar"){
            string todo;
            int todito;
            int i=0;
            cout<<"ingrese nombre"<<endl;
            cin>>todo;
            empleados.nombre=todo;
            cout<<"ingrese apellido"<<endl;
            cin>>todo;
            empleados.apellido=todo;
            cout<<"ingrese dia"<<endl;
            cin>>todito;
            empleados.fecha.dia=todito;
             cout<<"ingrese mes"<<endl;
            cin>>todito;
            empleados.fecha.mes=todito;
            cout<<"ingrese anio"<<endl;
            cin>>todito;
            empleados.fecha.anio=todito;
            cout<<"ingrese sexo"<<endl;
            cin>>todo;
            empleados.sexo=todo;
            cout<<"ingrese salario"<<endl;
            cin>>todito;
            empleados.salario=todito;
            empleado.push_back(empleados);

        }
    
    else if(nom=="salir"){
        cout<<"usted salio"<<endl;
    }
    else{
        cout<<"no esta esa opcion"<<endl;
    }
    }
}



empleado_t empleado_con_mayor_sueldo(vector<empleado_t> empleados, vector<empleado_t> &vacio){
    empleado_t empleadito=empleados[0];
    for(int i=0;i<empleados.size();i++){
        if(empleados[i].salario<400000){
            vacio.push_back(empleados[i]);
        }
        if(empleados[i].salario<empleadito.salario){
        
            
        }
    }
     return empleadito;

}

void mostrar(int numero,vector<empleado_t> empleados, vector<empleado_t> vacio){
    for(int i=0; i<vacio.size();i++){
        cout<<"nombre:"<<empleados[i].nombre<<' '<<"apellido:"<<empleados[i].apellido<<' '<<"cumple:"<<empleados[i].fecha.dia<<"/"<<empleados[i].fecha.mes<<"/"<<empleados[i].fecha.anio<<' '<<"sexo:"<<empleados[i].sexo<<' '<<"salario:"<<empleados[i].salario<<endl;
        
    }
    cout<<"empleado mejor pagado"<<endl;
    cout<<"nombre:"<<empleados[numero].nombre<<' '<<"apellido:"<<empleados[numero].apellido<<' '<<"cumple:"<<empleados[numero].fecha.dia<<"/"<<empleados[numero].fecha.mes<<"/"<<empleados[numero].fecha.anio<<' '<<"sexo:"<<empleados[numero].sexo<<' '<<"salario:"<<empleados[numero].salario<<endl;
}


int main(){
    int numero=0;
    vector<empleado_t> empleados;
    vector<empleado_t> vacio;
    cargavector(empleados);
    empleado_con_mayor_sueldo(numero,empleados,vacio);
    mostrar(numero,empleados,vacio);
    
    }
