## APIs - CADASTRO DE VEICULOS & PROJEÇÃO DE GASTOS.
### Documentação da API em Swagger.
* como acessar a documentação via Swagger: { servidor:porta/swagger-ui.html#/ }
#
### Dowglas Maia: dowglasmaia@live.com
### Linkedin: www.linkedin.com/in/dowglasmaia

```comunicação ente apis
01 -API: api-veiculos 
porta: 8081
---------------------
02 -API: api-projecao-gastos 
porta: 8082
```
#
### Tecnologias utilizadas no desenvolvimento:
```
* Linguagem de Programação: Java 11.
* Framework: Spring Boot - 2.3.8.RELEASE
* Banco de Dados: MySQL - v:8
* Gerenciador de Dependencias: Maven.
* Validação de Dados: Bean Validation
* Comunicação entre APIs: FeignClient
```
#
#### Testes:
```
01. Realizar o download das apis encontrados na 'Releases' aqui no github, ou realizar o download dos fontes, e em seguida o build e deploy das APIs - api-veiculos e api-projecao-gastos;
02. Realiar as requisições Utilzando uma ferramenta de teste de API REST, ou Através do swagger-ui, conforme exemplo abaixo.

- exemplo 1: http://localhost:8081/swagger-ui.html#/
- exemplo 2: http://localhost:8082/swagger-ui.html#/

Obs.: As configurações para conexão com o Banco de Dados MySQL8, são:
- porta: 3306
- username: root
- password: root
- nome da base: veiculodb
* Para rodar a api que estar disponivel para download no github, é necessário usar estas configurações.
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

## Payload para requisitar Projeção de Gastos:
* endpoint:  /api/v1/projecao-gastos
* method: GET
```
* parâmetros:
  precoLitroGasolina":Double,
  totalDeKmPercorridoNaCidade":Integer, 
  totalDeKmPercorridoNaRodovia":Integer

ex.: localhost:8082/api/v1/projecao-gastos?precoLitroGasolina=5.5&totalDeKmPercorridoNaCidade=20&totalDeKmPercorridoNaRodovia=200
 ```
 #
 #### Payload do Resposta da requisição de Projeção de Gastos .
 
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
## Regra para Cálculo da Projeção de Gastos.
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
