# ConfigServer Refresh
##Refresh All:
``
curl -X POST http://localhost:8888/bus/refresh
``
##Refresh Dedtination:
``
curl -X POST http://localhost:8888/bus/refresh?destination=orderweb:**
``
##Refresh One:
``
curl -X POST http://localhost:9001/refresh
``