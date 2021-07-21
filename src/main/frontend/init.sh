#!/bin/bash
echo "Initial script will setup npm packages for backend and frontend."

echo "Install frontend dependencies..."

echo "Install frontend dependencies..."
(cd ./frontend && npm install)

echo "Build frontend sources"
(cd ./frontend && npm run build)

echo "Process is finished."
