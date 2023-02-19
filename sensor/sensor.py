import network
import time
import urequests
import json
import machine
import dht
import ntptime
import sys

timeout = 0
# inicio del wifi 
wifi = network.WLAN(network.STA_IF)
wifi.active(False)
time.sleep(2)
wifi.active(True)
# conexicion
wifi.connect('WIFI_ESSID','WIFIPASS')


global temperatura
global humedad

print("Starting DHT22.")
d = dht.DHT22(machine.Pin(28))

while not wifi.isconnected():
    print("connecting...")
    while not wifi.isconnected() and timeout < 7:
        #print(wifi.status())
        print(7-timeout)
        timeout = timeout + 1
        time.sleep(1)
    
    if (wifi.isconnected()):
        print("conexcion a servidor de horario")
        ntptime.settime()   
    
    
    if timeout >= 7:
        print("error de conexion")
        timeout = 0
        continue



        
def formatofecha(tupla):
    mes = "0"+str(tupla[1]) if tupla[1]<10 else str(tupla[1])
    dia = "0"+str(tupla[2]) if tupla[2]<10 else str(tupla[2])
    hora = "0"+str(tupla[3]) if tupla[3]<10 else str(tupla[3])
    minuto = "0"+str(tupla[4]) if tupla[4]<10 else str(tupla[4])
    segundos = "0"+str(tupla[5]) if tupla[5]<10 else str(tupla[5])
       
    fecha = f"{tupla[0]}-{mes}-{dia}T{hora}:{minuto}:{segundos}.000+00:00"
    return fecha

def medir(objetodht):
    retry = 0
    while retry < 3:
        try:
            time.sleep(5)
            d.measure()
            break
        except:
            retry = retry + 1
            print(".", end = "")

    print("")

    if retry < 3:
        print("Temperature: %3.1f °C" % d.temperature())
        temperatura = d.temperature()
        print("   Humidity: %3.1f %% RH" % d.humidity())
        humedad = d.humidity()
        
    return temperatura , humedad

    
        
        
if (wifi.isconnected()):
    
    print("connected")
    url = "http://ip.del.servidor:8080/Condiciones/subir"
    while True:
        print(time.localtime())
        fecha = formatofecha(time.localtime())
        print(fecha)
        t , h = medir(d)
        payload = json.dumps({
          "fecha": fecha,
          "temperatura": t,
          "humedad": h
            })
        headers = {
          'Content-Type': 'application/json'
            }
        print(payload)
        try:
            req = urequests.request("POST", url, headers=headers, data=payload)
        except:
            print("req error")
            sys.exit()
            
            
        print(req.text)
        print(req.status_code)
        if (200 != req.status_code):
            print(req.status_code)
            sys.exit()
    
        req.close()
        time.sleep(15*60)
    
else:
    print("Time Out")

