export default function Lista({monitorok, DeleteMonitor}) {

    return (
        <div className='container'>
            {monitorok.map(x => <div className='hbox row' key={x.id}>
                <span>{x.tipus}: {x.meret}"</span>
                <div style={{flexGrow: 1}}></div>
                <div onClick={() => DeleteMonitor(x)} style={{cursor: "pointer", display: "flex", justifyContent: "center", alignItems: "center"}}><img title="Törlés" src='./kuka.png' /></div>
            </div>)}
        </div>
    )
}
