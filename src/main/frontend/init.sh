#!/bin/bash
echo "Initial script will setup npm packages for backend and frontend."

echo "Install frontend dependencies..."
(cd ./frontend && npm install)

echo "Build frontend sources"
(cd ./frontend && npm run build)

echo "Copying static files to resources"
(cd ./frontend && cp -a ./public/. ../../resources/static/)

echo "Process is finished."
