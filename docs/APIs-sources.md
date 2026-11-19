# **FOOTBALL-DATA.ORG**

## **URLS**

### **Documentation**
```http
https://www.football-data.org/documentation/api
```

### **BASE URL**
```http
https://api.football-data.org/v4
```

## **Responses**

### **Api response for matches**

- **competition**: `match` -> `competition` -> `name`
- **teams**: `homeTeam` / `awayTeam` -> `name`
- **dates**: `match` -> `utcDate` (because has the hours)
- **venue**: `match` -> `venue` (for validating, only the venue interested)

---

```json
{
    "head2head": {
        "numberOfMatches": 1,
        "totalGoals": 4,
        "homeTeam": {
            "wins": 1,
            "draws": 0,
            "losses": 0
        },
        "awayTeam": {
            "wins": 0,
            "draws": 0,
            "losses": 1
        }
    },
    "match": {
        "id": 200282,
        "competition": {
            "id": 2001,
            "name": "UEFA Champions League"
        },
        "season": {
            "id": 2,
            "startDate": "2017-06-27",
            "endDate": "2018-05-26",
            "currentMatchday": 6,
            "availableStages": [
                "1ST_QUALIFYING_ROUND",
                "2ND_QUALIFYING_ROUND",
                "3RD_QUALIFYING_ROUND",
                "PLAY_OFF_ROUND",
                "GROUP_STAGE",
                "ROUND_OF_16",
                "QUARTER_FINALS",
                "SEMI_FINALS",
                "FINAL"
            ]
        },
        "utcDate": "2018-05-26T18:45:00Z",
        "status": "FINISHED",
        "minute": null,
        "attendance": 61561,
        "venue": "NSK Olimpijs'kyj",
        "matchday": null,
        "stage": "FINAL",
        "group": "Final",
        "lastUpdated": "2018-06-22T10:00:12Z",
        "homeTeam": {
            "id": 86,
            "name": "Real Madrid CF",
            "coach": {
                "id": 79,
                "name": "Zinedine Zidane",
                "countryOfBirth": null,
                "nationality": null
            },
            "captain": {
                "id": 3192,
                "name": "Sergio Ramos",
                "shirtNumber": 4
            },
            "lineup": [
                {
                    "id": 43,
                    "name": "Luka Modrić",
                    "position": "Midfielder",
                    "shirtNumber": 10
                },
                {
                    "id": 44,
                    "name": "Cristiano Ronaldo",
                    "position": "Attacker",
                    "shirtNumber": 7
                }
            ],
            "bench": [
                {
                    "id": 3876,
                    "name": "Gareth Bale",
                    "position": "Midfielder",
                    "shirtNumber": 11
                },
                {
                    "id": 45,
                    "name": "Lucas Vázquez",
                    "position": "Midfielder",
                    "shirtNumber": 17
                }
            ]
        },
        "awayTeam": {
            "id": 64,
            "name": "Liverpool FC",
            "coach": {
                "id": 9344,
                "name": "Jürgen Klopp",
                "countryOfBirth": "Germany",
                "nationality": "Germany"
            },
            "captain": {
                "id": 3320,
                "name": "Jordan Henderson",
                "shirtNumber": 14
            },
            "lineup": [
                {
                    "id": 7861,
                    "name": "James Milner",
                    "position": "Defender",
                    "shirtNumber": 7
                }
            ],

            etc...
```
