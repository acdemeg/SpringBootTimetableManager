import React from 'react';
import './Error-indicator.css';

const ErrorIndicator = () => (
  <div className="error-indicator">
    <img src="/death-star.png" alt="error icon" width="256px" height="256px" />
    <span className="boom">BOOM!</span>
    <span>something has gone terribly wrong</span>
    <span>(but we already sent droids to fix it)</span>
  </div>
);

export default ErrorIndicator;
