import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { MantineProvider } from '@mantine/core'
import { Notifications } from '@mantine/notifications'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <MantineProvider withNormalizeCSS withGlobalStyles>
      <Notifications />
      <App />
    </MantineProvider>
  </React.StrictMode>,
)
