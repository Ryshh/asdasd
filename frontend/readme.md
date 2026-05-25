### GET
````
const [adatok, setAdatok] = useState([])

async function GetAdatok()
{
    let json = await (await fetch("http://localhost:88/get")).json()
    setAdatok(json)
}

useEffect(() => {
    GetAdatok()
}, [])
````
### POST/DELETE/INSERT
````
async function Post()
{
    let response = await (await fetch("http://localhost:88/post", {
        method: "POST",
        headers: {"Content-Type": "Application/json"},
        body: JSON.stringify({

        })
    })).json()
}
````
### Routing
````
// main.jsx
const router = createBrowserRouter([
{
    element: <Layout />, children: [
        { path: "/", element: <Home /> },
        { path: "/two", element: <Two /> },
        { path: "/about", element: <About /> },
        { path: "*", element: <Notfound /> }
    ]
}])

// main.jsx
<RouterProvider router={router} />

// app.jsx
<Outlet />

// any component
const navigate = useNavigate()
navigate("/home")

// page params -> path: "/users/:id" + /users/42 -> { id:42 }
const params = useParams()
````
