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
void llenagondola(productos_t gondola[3][4]){
    string nom;
    productos_t product;
    while(nom!="salir"){
    cout<<"Ingresar o Salir"<<endl;
    cin>>nom;
    if(nom=="ingresar"){
            string todo;
            int todito;
            int i=0;
            cout<<"ingrese nombre"<<endl;
            cin>>todo;
            product.nombre=todo;
            cout<<"ingrese codigo"<<endl;
            cin>>todito;
            product.codigo=todito;
            cout<<"ingrese marca"<<endl;
            cin>>todo;
            cout<<"ingrese precio"<<endl;
            cin>>todo;
            product.precio=todito;
            product.marca=todo;
            cout<<"ingrese dia"<<endl;
            cin>>todito;
            product.vencimiento.dia=todito;
            cout<<"ingrese anio"<<endl;
            cin>>todito;
            product.vencimiento.mes=todito;
            cout<<"ingrese dia"<<endl;
            cin>>todito;
            product.vencimiento.anio=todito;
            for(int i=0;i<3;i++){
                for(int j=0;j<4;j++){
                    gondola[i][j]=product;
                }
            }

        }
    
    else if(nom=="salir"){
        cout<<"usted salio"<<endl;
    }
    else{
        cout<<"no esta esa opcion"<<endl;
    }
    }
}
int mayor(productos_t gondola[3][4]){
    inbt max=0;
     for(int i=0;i<3;i++){
                for(int j=0;j<4;j++){
                    max=gondola[1][j].precio+gondola[2][j].precio+gondola[3][j].precio;
                    if


                }
            }




}

   





int main(){
    productos_t gondola[3][4];
    llenagondola(gondola);
    mayor(gondola);
}