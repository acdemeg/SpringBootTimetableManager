#!/bin/bash
echo "Build frontend sources"
(cd ./frontend && npm run build)

echo "Copying static files to resources"
(cd ./frontend && cp -a ./public/. ../../resources/static/)

echo "Process is finished."
