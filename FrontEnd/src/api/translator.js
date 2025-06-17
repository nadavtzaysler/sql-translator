import axios from 'axios'

const BASE_URL = `http://${window.location.hostname}:8080`

export function translateQuery (inputDialect, outputDialect, query) {
  return axios.post(
    `${BASE_URL}/translate`,
    {
      query
    },
    {
      params: {
        input: inputDialect,
        output: outputDialect
      }
    }
  )
}

export function fetchSupportedDialects () {
  return axios.get(`${BASE_URL}/supported`)
}
