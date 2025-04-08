const API_URL = "http://localhost:3000/api"

export const getCourses = async () => {
    const res = await fetch(`${API_URL}/courses`, {
        method: 'GET'
    })

    const data = res.json()

    if (!res.ok){
        throw data;
    }

    return data;
}

export const applyToCourse = async (id: number) => {
    const res = await fetch(`${API_URL}/courses/${id}/apply`, {
        method: 'POST'
    })

    const data = res.json()

    if (!res.ok){
        throw data;
    }

    return data;
}