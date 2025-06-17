import axios from 'axios'

const BASE_URL = window.location.hostname === 'localhost'
  ? 'http://localhost:8080'
  : 'http://sql-translator:8080'

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
