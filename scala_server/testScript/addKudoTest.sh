#!/bin/bash
curl -v -H "Content-Type: application/json" -X PUT --data "@kudos.json" localhost:8080/addKudo/
