import axios from 'axios'

export function translateQuery (backendUrl, inputDialect, outputDialect, query) {
  return axios.post(
    `${backendUrl}/translate`,
    {
      query
    },
    {
      params: {
        inputDialect: inputDialect,
        outputDialect: outputDialect
      }
    }
  )
}

export function fetchSupportedDialects (backendUrl) {
  return axios.get(`${backendUrl}/supported`)
}
