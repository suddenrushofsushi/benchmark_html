defmodule Mix.Tasks.Bench do
  use Mix.Task

  def run(_args) do
    path = "/Users/sushi/Research/tie/html/test_files"
    for file <- File.ls!(path), fp = Path.join(path, file) do
      new_path = String.replace(path, "test_files", "elixir_output") |> Path.join(file)
      outgoing = File.stream!(new_path, [:delayed_write])

      {:ok, file} = File.open(fp, [:utf8])
      try do
        content = IO.read(file, :all)
        |> Floki.find("a")
        |> Floki.raw_html
        IO.write(outgoing, content)
      rescue CaseClauseError -> "Whoa!"
      end
    end
  end
end
