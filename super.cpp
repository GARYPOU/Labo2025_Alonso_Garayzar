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
     for(int i=0;i<3;i++){
                for(int j=0;j<4;j++){
                    max=gondola[0][j].precio+gondola[1][j].precio+gondola[2][j].precio;
                      if(max>aux){
                        aux=max;
                        colum=j+1;


                    }
                    
                  
                }
   



        }
return colum;
}
void nom(productos_t gondola[3][4]){

    cout<<gondola[2][3].nombre<<endl;
    
}
int promedio(productos_t gondola[3][4]){
    int prom=0;
    for(int i=0; i<3; i++){
        for (int j=0; j<4; j++){
            prom=prom+gondola[i][j].precio;
            
        }


    }
    prom=prom/12;
    return prom;
}
   





int main(){
    productos_t gondola[3][4]={
        { 
            {"Laptop", 123, "Alienware", 1500, {12, 3, 2025}},
            {"Smartphone", 456, "Samsung", 800, {12, 3, 2025}},
            {"Tablet", 789, "Apple", 1000, {12, 3, 2025}},
            {"Auriculares", 101, "Sony", 120, {12, 3, 2025}}
        },
        { 
            {"Reloj", 202, "Garmin", 250, {12, 3, 2025}},
            {"Cámara", 303, "Canon", 600, {12, 3, 2025}},
            {"Teclado", 404, "Corsair", 130, {12, 3, 2025}},
            {"Ratón", 505, "Logitech", 90, {12, 3, 2025}}
        },
        { 
            {"Monitor", 606, "Samsung", 350, {12, 3, 2025}},
            {"Consola", 707, "PlayStation", 500, {12, 3, 2025}},
            {"Altavoces", 808, "Bose", 200, {12, 3, 2025}},
            {"TV", 909, "LG", 750, {12, 3, 2025}}
        }
    };
   
    mayor(gondola);
    cout<<mayor(gondola)<<endl;
}
