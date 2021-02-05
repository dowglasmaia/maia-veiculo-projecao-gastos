## API REST - CADASTRO DE VEICULOS & PREVISÃO DE GASTOS.
#
### Dowglas Maia: dowglasmaia@live.com
### Linkedin: www.linkedin.com/in/dowglasmaia

#
### Tecnologias utilizadas no desenvolvimento:
```
Linguagem de Programação: Java 11.
Framework: Spring Boot - 2.3.8.RELEASE
Banco de Dados: MySQL
Gerenciador de Dependencias: Maven.
```

### Documentação da API em Swagger.
´ para acessar a documentção via Swagger siga o exemplo: servidor/swagger-ui.html#/ ´ 
```Teste de API
exemplo: http://localhost:8080/swagger-ui.html#/
```
#
## Payload para cadastro de Veiculo:
* endpoint:  /api/v1/veiculos/
* method: POST
```
{
"nome":"String",
"marca":"String",
"modelo":"String",
"dataDeFabricacao":"Date: ex. 2015-02-04",
"consumoMedioDeCombustivelInCidade":Integer,
"consumoMedioDeCombustivelEmRodovia":Integer
}
 ```
 ### Obs. : Tambem é possivel listar todos os veiculos cadastrados na Base de Dados ou listar Apenas Um pelo seu ID:
```
endpoint: /api/v1/veiculos/
method: GET
``` 
```
endpoint: /api/v1/veiculos/id
method: GET
``` 

# 

## Payload para requisitar Previsão de Gastos:
* endpoint:  /api/v1/previsoes-gastos
* method: POST
```
{
"precoLitroGasolina":5.60,
"totalDeKmPercorridoNaCidade":50,
"totalDeKmPercorridoNaRodovia":150
}
 ```
 #
 #### Payload do Resposta da requisição de Previsão de Gastos .
 
 ```
 [
  {
    "id": Integer,
    "nome": "String",
    "marca": "String",
    "modelo": "String",
    "anoFabricacao": Integer,
    "qtdaCombustivelGasto": Integer,
    "valorTotalGastoComCombustivel": BigDecimal
  }
 ...
]
 Obs: A Resposta retorna uma Lista com todos os veiculos, e as previsões de gastos de acordo com o consume de cada Veiculo.
 ```

#
## Regra para Calculo da Previsão de Gastos.
```Regra

        --------------------------------
        | CTC-CID = CMC-KML * TKM-PC; |

        | CTC-ROD = CMR-KML * TKM-PR;  |

        | CTC-LTS = CTC-CID + CTC-ROD  |

        | VLR-TGC = PCG * CTC-LTS      |
        --------------------------------

* Consumo Médio de combustível dentro de cidade (KM/L) = CMC-KML´ ;

* Consumo Médio de combustível em rodovias (KM/L) = ´CMR-KML´;

* Total de km que será percorrido dentro da cidade = `TKM-PC`;

* Total de km que será percorrido em rodovias = `TKM-PR`;

* Preço da gasolina R$ = `PCG`; 

* Consumo Total De Combustivel Na Cidade = CTC-CID

* Consumo Total De Combustivel Na Rodovia = CTC-ROD

* Consumo Total De Combustivel Em Litros = CTC-LTS

* Valor Total Gasto Com Combustivel Em Reais = VLR-TGC
```
