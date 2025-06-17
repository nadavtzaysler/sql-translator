export async function fetchRuntimeConfig () {
  try {
    const res = await fetch('/config.json')
    if (!res.ok) throw new Error('Config load failed')
    return await res.json()
  } catch (err) {
    console.error('Failed to load config:', err)
    return {}
  }
}
