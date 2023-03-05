#!/bin/sh

mvn clean test \
  -DKEY=${{ secrets.KEY }} \
  -DTOKEN=${{ secrets.TOKEN }} \
  -DORGANIZATION_ID=${{ secrets.ORGANIZATION_ID }}