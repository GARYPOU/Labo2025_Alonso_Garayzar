using namespace std;
#include <iostream>
#include <vector>
struct date{
    int dia;
    int mes;
    int anio;
};
struct productos_t{
    string nombre;
    int codigo;
    string marca;
    int precio;
    date vencimiento;
};

int mayor(productos_t gondola[3][4]){
    int max=0;
    int aux=0;
    int colum=0;
        for(int j=0;j<4;j++){
                    max=gondola[1][j].precio+gondola[2][j].precio+gondola[3][j].precio;
                      if(max>aux){
                        aux=max;
                        colum=j+1;


                    }
                    max=0;
                  
                }
   



        
return colum;
}
void nom(productos_t gondola[3][4]){

    cout<<gondola[1][2].nombre<<endl;
    
}
int promedio(productos_t gondola[3][4]){
    int prom=0;
    int filas=0;
    int colum=0;
    for(int i=0; i<3; i++){
        for (int j=0; j<4; j++){
            prom=prom+gondola[i][j].precio;
            filas=i;
            colum=j;
        }

  
    }
    prom=prom/(filas*colum);
    return prom;
}
int venc(productos_t gondola[3][4]){
    int contador=0;
     for(int i=0;i<3;i++){
                for(int j=0;j<4;j++){
                    if(gondola[i][j].vencimiento.dia>0 or gondola[i][j].vencimiento.mes>0 or gondola[i][j].vencimiento.anio>0){
                      
                        contador=contador+1;
                        


                    }
                    
                  
                }
   



        }
return contador;
}
   





int main(){
    productos_t gondola[3][4]={
        { 
            {"Laptop", 123, "Alienware", 15000, {}},
            {"salame", 456, "Samsung", 800, {12, 3, 2025}},
            {"Tablet", 789, "Apple", 1000, {}},
            {"Auriculares", 101, "Sony", 120, {}}
        },
        { 
            {"Reloj", 202, "Garmin", 250, {}},
            {"Cámara", 303, "Canon", 600, {}},
            {"Yogurt", 404, "Corsair", 130, {12, 3, 2025}},
            {"queso", 505, "Logitech", 90, {12, 3, 2025}}
        },
        { 
            {"Monitor", 606, "Samsung", 3500, {}},
            {"Leche", 707, "PlayStation", 5000, {12, 3, 2025}},
            {"Altavoces", 808, "Bose", 300, {}},
            {"TV", 909, "LG", 75000, {}}
        }
    };
    cout<<mayor(gondola)<<endl;
    nom(gondola);
    cout<<promedio(gondola)<<endl;
    cout<<venc(gondola)<<endl;
}
