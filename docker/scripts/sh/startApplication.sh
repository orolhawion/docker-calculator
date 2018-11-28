#!/usr/bin/env bash

set -e

START_MODE="normal"
BIND_ADDRESS=`hostname -I`

echo "Starte Wildfly mit start-mode '${START_MODE}' und bind-address '${BIND_ADDRESS}'"

/opt/jboss/wildfly/bin/standalone.sh \
    -c=standalone-full.xml \
    -b=$BIND_ADDRESS \
    -bmanagement=${BIND_ADDRESS} \
    -Duser.language=en \
    --start-mode=${START_MODE}
