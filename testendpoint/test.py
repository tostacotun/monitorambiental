import requests as rq


def main():
    print("este script verifica la integridad del backend y la base de datos")
    url = "http://127.0.0.1:8080"
    parametros = {
        "tiempo_inicial": "1676748099756",
        "tiempo_final": "1676730099756"
    }
    resultado = rq.get(url+"/Condiciones/filtrar",params=parametros)
    print(resultado.url)
    if (resultado.status_code!= 200):
        print("error general")

    print(resultado.json())

main()
